package com.beyond.be4th4teambackend.qna.dto;

import com.beyond.be4th4teambackend.qna.entity.Qna;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QnAEditDto {

    private long id;

    private String title;

    private String content;

    private LocalDateTime updatedAt;

    public static QnAEditDto fromEntity(Qna qna) {
        return QnAEditDto.builder()
                .id(qna.getId())
                .content(qna.getContent())
                .title(qna.getTitle())
                .updatedAt(qna.getUpdatedAt())
                .build();
    }

}
