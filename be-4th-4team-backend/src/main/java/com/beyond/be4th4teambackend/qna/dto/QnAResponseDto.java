package com.beyond.be4th4teambackend.qna.dto;

import com.beyond.be4th4teambackend.qna.entity.Qna;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QnAResponseDto {
    private long id;

    private String title;

    private String content;

    private Qna qna;

    private List<Qna> replies;

    public static QnAResponseDto fromEntity(Qna qna) {
        return QnAResponseDto.builder()
                .id(qna.getId())
                .content(qna.getContent())
                .title(qna.getTitle())
                .qna(qna.getParent())
                .replies(qna.getReplies())
                .build();
    }
}
