package com.beyond.be4th4teambackend.auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class JwtTokenProvider {
    private final SecretKey secretKey;
    private final UserDetailsService userDetailsService;
    private final RedisTemplate<String, String> redisTemplate;
    private static final long ACCESS_TOKEN_EXP = 1000L * 60L * 15L; // 15분
    private static final long REFRESH_TOKEN_EXP = 1000L * 60L * 60L * 24L; // 24시간

    public JwtTokenProvider(
            @Value("${springboot.jwt.secret}") String secret,
            @Lazy UserDetailsService userDetailsService,
            RedisTemplate<String, String> redisTemplate
    ) {
        log.debug("Secret: {}", secret);

        this.secretKey = new SecretKeySpec(
                secret.getBytes(StandardCharsets.UTF_8),
                Jwts.SIG.HS256.key().build().getAlgorithm()
        );
        this.userDetailsService = userDetailsService;
        this.redisTemplate = redisTemplate;
    }

    public String createAccessToken(String sub, String username, String email) {
        Map<String, String> claims = new HashMap<>();
        claims.put("sub", sub);
        claims.put("email", email);
        claims.put("username", username);

        return createToken(claims, ACCESS_TOKEN_EXP);
    }

    public String createRefreshToken(String sub, String username, String email) {
        Map<String, String> claims = new HashMap<>();
        claims.put("username", username);
        claims.put("email", email);
        claims.put("sub", sub);

        return createToken(claims, REFRESH_TOKEN_EXP);
    }

    private String createToken(Map<String, String> claims, long tokenExp) {
        return Jwts.builder()
                .header().add("typ", "JWT").and()
                .claims(claims)
                .id(Long.toHexString(System.nanoTime()))
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + tokenExp))
                .signWith(secretKey)
                .compact();
    }

    public boolean validateToken(String token) {
        return !getClaims(token).getExpiration().before(new Date());
    }

    public String resolveToken(String bearerToken) {
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public Authentication getAuthentication(String token) {
        String email = getUserEmail(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        return new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );
    }

    public String getUserEmail(String token) {
        return getClaims(token).get("email").toString();
    }

    public void addBlackList(String accessToken) {
        String key = "blacklist:" + getJti(accessToken);

        redisTemplate.opsForValue().set(key, "true", ACCESS_TOKEN_EXP, TimeUnit.MILLISECONDS);
    }

    private String getJti(String token) {
        return getClaims(token).getId();
    }

    public void deleteRefreshToken(String accessToken) {
        String email = getUserEmail(accessToken);

        redisTemplate.delete("refresh:" + email);
    }

    public boolean hasRole(String token) {
        return getClaims(token).get("role") != null;
    }

    private Claims getClaims(String token) {
        try {
            return Jwts
                    .parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    public boolean isValidRefreshToken(String refreshToken) {
        String email = getUserEmail(refreshToken);
        String storedRefreshToken = redisTemplate.opsForValue().get("refresh:" + email);


        return storedRefreshToken != null && storedRefreshToken.equals(refreshToken);
    }
}
