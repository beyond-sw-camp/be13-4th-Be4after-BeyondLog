package com.beyond.be4th4teambackend.qna.entity;

import com.beyond.be4th4teambackend.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@MappedSuperclass
@SuperBuilder
@NoArgsConstructor
public abstract class BaseQnAEntity extends BaseEntity {
    // id, 생성일, 수정일 상속받음

    // 추후에 추가될 유저 테이블 고려
    // private User user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    public void setTitle(String title) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("제목은 비어있을 수 없습니다.");
        }
        this.title = title;
    }

    public void setContent(String content) {
        if (content == null || content.isEmpty()) {
            throw new IllegalArgumentException("내용은 비어있을 수 없습니다.");
        }
        this.content = content;
    }
}
