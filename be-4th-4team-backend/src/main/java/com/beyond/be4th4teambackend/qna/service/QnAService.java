package com.beyond.be4th4teambackend.qna.service;

import com.beyond.be4th4teambackend.qna.dto.QnACreateRequestDto;
import com.beyond.be4th4teambackend.qna.dto.QnAEditDto;
import com.beyond.be4th4teambackend.qna.dto.QnAResponseDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface QnAService {
    @Transactional
    QnAResponseDto createQnA(Long parentId, QnACreateRequestDto responseDto);

    // qna 가져오기
    @Transactional
    List<QnAResponseDto> getQnA();

    // 수정
    @Transactional
    QnAEditDto updateQna(Long parentId, QnACreateRequestDto responseDto);

    // 삭제
    @Transactional
    void deleteQna(Long QnaNo);
}
