package com.beyond.be4th4teambackend.qna.service;

import com.beyond.be4th4teambackend.qna.dto.QnACreateRequestDto;
import com.beyond.be4th4teambackend.qna.dto.QnAEditDto;
import com.beyond.be4th4teambackend.qna.dto.QnAResponseDto;
import com.beyond.be4th4teambackend.qna.entity.Qna;
import com.beyond.be4th4teambackend.qna.repository.QnARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QnAServiceImpl implements QnAService {

    private final QnARepository qnARepository;

    // 질문 생성
    @Transactional
    @Override
    public QnAResponseDto createQnA(Long parentId, QnACreateRequestDto responseDto){
        // 유저 관련 로직

        Qna  parentQna = null;

        if (parentId != null){
            parentQna = qnARepository.findById(parentId)
                    .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        }

        Qna qna = Qna.builder()
                .title(responseDto.getTitle())
                .content(responseDto.getContent())
                .parent(parentQna)
                .build();
        Qna saveQna = qnARepository.save(qna);

        return QnAResponseDto.fromEntity(saveQna);
    }

    // qna 가져오기
    @Transactional
    @Override
    public List<QnAResponseDto> getQnA(){
        List<Qna> qnaList = qnARepository.findAll();
        return qnaList.stream()
                .filter(qna -> qna.getParent() == null)
                .map(QnAResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

    // 수정
    @Transactional
    @Override
    public QnAEditDto updateQna(Long parentId, QnACreateRequestDto responseDto){

        Qna qna = qnARepository.findById(parentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        qna.setTitle(responseDto.getTitle());
        qna.setContent(responseDto.getContent());

        Qna updatedQna = qnARepository.save(qna);

        return QnAEditDto.fromEntity(updatedQna);
    }

    // 삭제
    @Transactional
    @Override
    public void deleteQna(Long QnaNo){
        // 작성자 확인 로직 (임시)

        qnARepository.deleteById(QnaNo);
    }
}
