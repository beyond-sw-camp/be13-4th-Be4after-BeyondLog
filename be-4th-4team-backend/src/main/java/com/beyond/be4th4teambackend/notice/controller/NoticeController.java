package com.beyond.be4th4teambackend.notice.controller;

import com.beyond.be4th4teambackend.auth.dto.ResponseDto;
import com.beyond.be4th4teambackend.notice.data.dto.NoticeRequestDto;
import com.beyond.be4th4teambackend.notice.data.dto.NoticeResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface NoticeController {

    @Operation(summary = "공지 게시글 등록 메서드", description = "공지 게시글을 등록합니다.")
    ResponseEntity<ResponseDto<NoticeResponseDto>> createNotice(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody NoticeRequestDto requestDto);

    @Operation(summary = "공지 게시글 수정 메서드", description = "공지 게시글을 수정합니다.")
    ResponseEntity<ResponseDto<NoticeResponseDto>> updateNotice(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long noticeId,
            @RequestBody NoticeRequestDto requestDto);

    @Operation(summary = "공지 게시글 삭제 메서드", description = "공지 게시글을 삭제합니다.")
    ResponseEntity<ResponseDto<Void>> deleteNotice(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long noticeId);

    @Operation(summary = "공지 게시글 조회 메서드", description = "공지 게시글을 조회합니다.")
    ResponseEntity<ResponseDto<NoticeResponseDto>> getNotice(@PathVariable Long noticeId);

    @Operation(summary = "공지 게시판 조회 메서드", description = "공지 게시판을 조회합니다.")
    ResponseEntity<ResponseDto<Page<NoticeResponseDto>>> getNoticePosts(Pageable pageable);
}
