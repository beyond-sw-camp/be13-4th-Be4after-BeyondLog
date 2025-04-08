package com.beyond.be4th4teambackend.notice.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@Entity
@NoArgsConstructor
@SuperBuilder
//@Table(name = "notice")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "notice_id")),
        @AttributeOverride(name = "title", column = @Column(name = "notice_title")),
        @AttributeOverride(name = "content", column = @Column(name = "notice_content"))
})

public class Notice extends BaseNoticeEntity {
}