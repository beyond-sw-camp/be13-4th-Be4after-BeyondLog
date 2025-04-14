package com.beyond.be4th4teambackend.auth.controller;

import com.beyond.be4th4teambackend.auth.dto.LoginRequestDto;
import com.beyond.be4th4teambackend.auth.dto.ResponseDto;
import com.beyond.be4th4teambackend.auth.dto.TokenResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@Tag(name = "Authentication API", description = "사용자 인증 관련 API")
public interface AuthController {

    @Operation(summary = "로그인", description = "로그인 성공 시 JWT 토큰 반환")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful login",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(mediaType = "application/json")
            )
    })
    ResponseEntity<ResponseDto<TokenResponseDto>> login(@Valid @RequestBody LoginRequestDto loginRequestDto);

    @Operation(summary = "로그아웃", description = "Access Token을 전달받아 로그아웃한다.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "NO CONTENT",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "INTERNAL SERVER ERROR",
                    content = @Content(mediaType = "application/json")
            )
    })
    ResponseEntity<ResponseDto<Void>> logout(@RequestHeader("Authorization") String bearerToken);

}
