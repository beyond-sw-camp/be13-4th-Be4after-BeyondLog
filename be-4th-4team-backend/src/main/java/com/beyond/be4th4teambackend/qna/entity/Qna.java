package com.beyond.be4th4teambackend.qna.entity;

import com.beyond.be4th4teambackend.auth.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@SuperBuilder
@Getter
@Table(name = "qna")
@NoArgsConstructor
@AllArgsConstructor
public class Qna extends BaseQnAEntity{

    // 다대일, 필요할때만 데이터 가져옴
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore // 객체를 Josn으로 변환할때 순환참조 방지
    @JoinColumn(name = "parent_id")
    private Qna parent; // 부모 글 (질문이면 null, 답변이면 해당 질문의 ID)

    // 일대다, parent 필드 기준 연결, 부모 삭제되면 자식도 삭제
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Qna> replies = new ArrayList<>(); // 연결된 답변들
}
