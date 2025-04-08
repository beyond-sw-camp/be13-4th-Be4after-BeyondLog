package com.beyond.be4th4teambackend.notice.data;

import com.beyond.be4th4teambackend.notice.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
}