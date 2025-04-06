package com.beyond.be4th4teambackend.auth.service;

import com.beyond.be4th4teambackend.auth.dto.TokenResponseDto;
import com.beyond.be4th4teambackend.auth.entity.User;
import com.beyond.be4th4teambackend.auth.jwt.JwtTokenProvider;
import com.beyond.be4th4teambackend.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public TokenResponseDto login(String email, String password) {
        User user = userRepository.findByUserEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("이메일이 없습니다"));

        if(!passwordEncoder.matches(password, user.getPassword())) {
            String encoded = passwordEncoder.encode("1234");
            System.out.println("Encoded password: " + encoded);
            System.out.println("password---------->"+password);
            System.out.println("user.getPassword()---------->"+user.getPassword());
            System.out.println(passwordEncoder.matches(password, user.getPassword()));
            throw new IllegalArgumentException("비밀번호가 올바르지 않습니다.");
        }

        return new TokenResponseDto(
                jwtTokenProvider.createAccessToken(String.valueOf(user.getId()),
                        user.getUsername(),
                        user.getUserEmail()
                ),
                jwtTokenProvider.createRefreshToken(String.valueOf(user.getId()), user.getUsername(), user.getUserEmail())
        );
    }

    @Override
    public void logout(String bearerToken) {
        String accessToken = jwtTokenProvider.resolveToken(bearerToken);

        if (accessToken == null || !jwtTokenProvider.validateToken(accessToken)) {
            throw new IllegalArgumentException("아이디 또는 비밀번호가 올바르지 않습니다.");
        }

        jwtTokenProvider.addBlackList(accessToken);
        jwtTokenProvider.deleteRefreshToken(accessToken);

    }
}
