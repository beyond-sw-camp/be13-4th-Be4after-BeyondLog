package com.beyond.be4th4teambackend.qna.controller;

import com.beyond.be4th4teambackend.qna.dto.QnACreateRequestDto;
import com.beyond.be4th4teambackend.qna.dto.QnAResponseDto;
import com.beyond.be4th4teambackend.qna.entity.Qna;
import com.beyond.be4th4teambackend.qna.service.QnAService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/qna")
@Tag(name = "Q&A API", description = "Q&A 관련 API 목록")
public class QnAController {

    private final QnAService qnaService;

    // 작성
    @PostMapping
    @Operation(summary = "Q&A 작성", description =  "Q&A를 작성합니다.")
    public ResponseEntity<QnAResponseDto> createQna(
            @RequestParam(required = false) Long parentId,
            @RequestBody QnACreateRequestDto requestDto){

        return ResponseEntity.ok(qnaService.createQnA(parentId,requestDto)); // 원글 작성
    }

    // 조회
    @GetMapping
    @Operation(summary = "Q&A 조회", description = "Q&A를 조회합니다.")
    public ResponseEntity<List<Qna>>getQnAs(){
        return ResponseEntity.ok(qnaService.getQnA());
    }

    // 수정
    @PutMapping("/{qnaId}")
    @Operation(summary = "Q&A 수정", description = "Q&A를 수정합니다.")
    public ResponseEntity<QnAResponseDto> updateQna(
            @PathVariable Long qnaId,
            @RequestBody QnACreateRequestDto requestDto){
        return ResponseEntity.ok(qnaService.updateQna(qnaId,requestDto));
    }

    // 삭제
    @DeleteMapping("/{qnaId}")
    @Operation(summary = "Q&A 삭제", description = "Q&A를 삭제합니다.")
    public ResponseEntity<String> deleteQna(@PathVariable Long qnaId){
        qnaService.deleteQna(qnaId);
        return ResponseEntity.status(HttpStatus.OK).body("Qna가 삭제되었습니다.");
    }
}
