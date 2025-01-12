package com.sns255.sns255.domain.message.dto.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class MessageRequestDto {

    @NotNull(message = "수신자는 필수입니다.")
    private Long receiverId;

    @NotBlank(message = "메시지 내용은 필수입니다.")
    private String content;

    public MessageRequestDto(Long receiverId, String content) {
        this.receiverId = receiverId;
        this.content = content;
    }
}
