package com.beyond.be4th4teambackend.qna.service;

import com.beyond.be4th4teambackend.qna.dto.QnACreateRequestDto;
import com.beyond.be4th4teambackend.qna.dto.QnAResponseDto;
import com.beyond.be4th4teambackend.qna.entity.Qna;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface QnAService {
    @Transactional
    QnAResponseDto createQnA(Long parentId, QnACreateRequestDto responseDto);

    // qna 가져오기
    @Transactional
    List<Qna> getQnA();

    // 수정
    @Transactional
    QnAResponseDto updateQna(Long parentId, QnACreateRequestDto responseDto);

    // 삭제
    @Transactional
    void deleteQna(Long QnaNo);
}
