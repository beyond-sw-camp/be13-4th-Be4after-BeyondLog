package com.beyond.be4th4teambackend.notice.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class NoticeResponseDto {
    private final Long noticeId;
    private final String noticeTitle;
    private final String noticeContent;
    private final Long authorId;
    private final int noticeViews;
    private final LocalDateTime createDate;
    private final LocalDateTime updateDate;
}