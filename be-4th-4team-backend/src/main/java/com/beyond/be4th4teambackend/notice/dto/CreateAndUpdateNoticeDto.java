package com.beyond.be4th4teambackend.notice.dto;

import lombok.Getter;

@Getter
public class CreateAndUpdateNoticeDto {
    private Long userId;
    private String title;
    private String content;
}
