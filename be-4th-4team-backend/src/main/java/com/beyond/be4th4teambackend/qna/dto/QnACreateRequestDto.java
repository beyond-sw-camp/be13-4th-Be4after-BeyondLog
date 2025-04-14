package com.beyond.be4th4teambackend.qna.dto;

import com.beyond.be4th4teambackend.auth.entity.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QnACreateRequestDto {

    private Long userId;

    @NotBlank(message = "title은 필수 입력값입니다.")
    private String title;

    @NotBlank(message = "content는 필수 입력값입니다.")
    private String content;

}
