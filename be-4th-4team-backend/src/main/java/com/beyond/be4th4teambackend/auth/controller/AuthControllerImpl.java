package com.beyond.be4th4teambackend.auth.controller;

import com.beyond.be4th4teambackend.auth.dto.LoginRequestDto;
import com.beyond.be4th4teambackend.auth.dto.ResponseDto;
import com.beyond.be4th4teambackend.auth.dto.TokenResponseDto;
import com.beyond.be4th4teambackend.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    @Override
    public ResponseEntity<ResponseDto<TokenResponseDto>> login(
            @Valid @RequestBody LoginRequestDto loginRequestDto) {
        TokenResponseDto tokenResponseDto = authService.login(
                loginRequestDto.getEmail(),
                loginRequestDto.getPassword()
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        new ResponseDto<>(
                                HttpStatus.CREATED.value(),
                                "Login Success",
                                true,
                                tokenResponseDto
                        ));
    }

    @PostMapping("/logout")
    @Override
    public ResponseEntity<ResponseDto<Void>> logout(@RequestHeader("Authorization") String bearerToken) {
        authService.logout(bearerToken);

        return ResponseEntity.ok(
                new ResponseDto<>(
                        HttpStatus.OK.value(),
                        "Logout Success",
                        true,
                        null
                ));
    }
}
