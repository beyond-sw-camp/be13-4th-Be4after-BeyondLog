package com.beyond.be4th4teambackend.notice.service;

import com.beyond.be4th4teambackend.auth.entity.User;
import com.beyond.be4th4teambackend.auth.repository.UserRepository;
import com.beyond.be4th4teambackend.notice.dto.CreateAndUpdateNoticeDto;
import com.beyond.be4th4teambackend.notice.dto.NoticeResponseDto;
import com.beyond.be4th4teambackend.notice.entity.Notice;
import com.beyond.be4th4teambackend.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {
    private final NoticeRepository noticeRepository;
    private final UserRepository userRepository;

    @Override
    public NoticeResponseDto createNotice(CreateAndUpdateNoticeDto createNoticeDto) {
        User user = userRepository.findById(createNoticeDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Notice notice = Notice.builder()
                .title(createNoticeDto.getTitle())
                .content(createNoticeDto.getContent())
                .user(user)
                .build();
        noticeRepository.save(notice);

        return NoticeResponseDto.fromEntity(notice);
    }

    @Override
    public NoticeResponseDto updateNotice(Long noticeId, CreateAndUpdateNoticeDto updateNoticeDto) {
        Notice notice = noticeRepository.findById(noticeId)
                .orElseThrow(() -> new RuntimeException("Not Exist Notice"));

        notice.setTitle(updateNoticeDto.getTitle());
        notice.setContent(updateNoticeDto.getContent());
        noticeRepository.save(notice);
        return NoticeResponseDto.fromEntity(notice);
    }

    @Override
    public void deleteNotice(Long userId, Long noticeId) {
        Notice notice = noticeRepository.findById(noticeId)
                .orElseThrow(() -> new RuntimeException("Not Exist Notice"));

        if (notice.getUser().getId() != userId) {
            throw new RuntimeException("User not authorized to delete notice");
        }

        noticeRepository.deleteById(noticeId);
    }

    @Override
    public List<NoticeResponseDto> getNoticePosts() {
        return noticeRepository.findAll().stream().map(NoticeResponseDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public NoticeResponseDto getNoticeById(Long noticeId) {
        Notice notice = noticeRepository.findById(noticeId)
                .orElseThrow(() -> new RuntimeException("Not Exist Notice"));
        return NoticeResponseDto.fromEntity(notice);
    }
}
