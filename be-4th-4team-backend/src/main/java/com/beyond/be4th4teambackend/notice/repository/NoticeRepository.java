package com.beyond.be4th4teambackend.notice.repository;

import com.beyond.be4th4teambackend.notice.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
