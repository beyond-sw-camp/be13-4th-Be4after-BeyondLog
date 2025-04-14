package com.beyond.be4th4teambackend.auth.service;

import com.beyond.be4th4teambackend.auth.dto.TokenResponseDto;

public interface AuthService {
    TokenResponseDto login(String email, String password);

    void logout(String bearerToken);
}
