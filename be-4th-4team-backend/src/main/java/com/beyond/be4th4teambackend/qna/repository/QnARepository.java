package com.beyond.be4th4teambackend.qna.repository;

import com.beyond.be4th4teambackend.qna.entity.Qna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QnARepository extends JpaRepository<Qna,Long> {
}
