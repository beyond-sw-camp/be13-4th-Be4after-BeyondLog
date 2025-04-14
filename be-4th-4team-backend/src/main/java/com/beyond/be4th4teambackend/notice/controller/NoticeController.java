package com.beyond.be4th4teambackend.notice.controller;

import com.beyond.be4th4teambackend.notice.dto.CreateAndUpdateNoticeDto;
import com.beyond.be4th4teambackend.notice.dto.NoticeResponseDto;
import com.beyond.be4th4teambackend.notice.service.NoticeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/v1/notice")
//@Tag(name = "Notice API", description = "공지 관련 API 목록")
//public class NoticeController {
//    private final NoticeService noticeService;
//
//    @PostMapping
//    @Operation(summary = "공지 작성", description =  "공지를 작성합니다.")
//    public ResponseEntity<NoticeResponseDto> createNotice(@RequestBody CreateAndUpdateNoticeDto createNoticeDto) {
//        return ResponseEntity.status(HttpStatus.CREATED).body(noticeService.createNotice(createNoticeDto));
//    }
//
//    @GetMapping
//    @Operation(summary = "공지 조회", description = "공지를 조회합니다.")
//    public ResponseEntity<List<NoticeResponseDto>> getNotices() {
//        return ResponseEntity.ok(noticeService.getNoticePosts());
//    }
//
//    @GetMapping("/{noticeId}")
//    @Operation(summary = "공지 상세 조회", description = "해당 공지를 상세 조회합니다.")
//    public ResponseEntity<NoticeResponseDto> getNotice(@PathVariable Long noticeId) {
//        return ResponseEntity.ok(noticeService.getNoticeById(noticeId));
//    }
//
//    @PutMapping("/{noticeId}")
//    @Operation(summary = "공지 수정", description = "공지를 수정합니다.")
//    public ResponseEntity<NoticeResponseDto> updateNotice(@PathVariable Long noticeId,
//                                                          @RequestBody CreateAndUpdateNoticeDto updateNoticeDto) {
//        return ResponseEntity.status(HttpStatus.CREATED)
//                .body(noticeService.updateNotice(noticeId, updateNoticeDto));
//    }
//
//    @DeleteMapping("/{userId}/{noticeId}")
//    @Operation(summary = "공지 삭제", description = "공지를 삭제합니다.")
//    public ResponseEntity<Void> deleteNotice(@PathVariable Long userId,
//                                             @PathVariable Long noticeId) {
//        noticeService.deleteNotice(userId, noticeId);
//        return ResponseEntity.ok().build();
//    }
//}
