package com.beyond.be4th4teambackend.notice.service;

import com.beyond.be4th4teambackend.notice.dto.CreateAndUpdateNoticeDto;
import com.beyond.be4th4teambackend.notice.dto.NoticeResponseDto;

import java.util.List;

public interface NoticeService {
    NoticeResponseDto createNotice(CreateAndUpdateNoticeDto createNoticeDto);

    NoticeResponseDto updateNotice(Long noticeId, CreateAndUpdateNoticeDto createNoticeDto);

    void deleteNotice(Long userId, Long noticeId);

    List<NoticeResponseDto> getNoticePosts();

    NoticeResponseDto getNoticeById(Long noticeId);
}
