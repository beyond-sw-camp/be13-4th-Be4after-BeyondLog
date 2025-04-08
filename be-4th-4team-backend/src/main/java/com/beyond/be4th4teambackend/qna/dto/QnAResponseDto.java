package com.beyond.be4th4teambackend.qna.dto;

import com.beyond.be4th4teambackend.auth.entity.User;
import com.beyond.be4th4teambackend.qna.entity.Qna;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QnAResponseDto {
    private long id;

    private User user;

    private String title;

    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Qna qna;

    private List<Qna> replies;

    public static QnAResponseDto fromEntity(Qna qna) {
        return QnAResponseDto.builder()
                .id(qna.getId())
                .user(qna.getUser())
                .content(qna.getContent())
                .title(qna.getTitle())
                .createdAt(qna.getCreatedAt())
                .updatedAt(qna.getUpdatedAt())
                .qna(qna.getParent())
                .replies(qna.getReplies())
                .build();
    }
}
