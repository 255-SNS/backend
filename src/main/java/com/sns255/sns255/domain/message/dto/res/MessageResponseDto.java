package com.sns255.sns255.domain.message.dto.res;

import com.sns255.sns255.domain.message.entity.Message;
import com.sns255.sns255.domain.message.entity.MessageStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class MessageResponseDto {
    private Long id;
    private Long senderId;
    private Long receiverId;
    private String content;
    private MessageStatus status;
    private LocalDateTime createdAt;

    public static MessageResponseDto fromEntity(Message message) {
        return MessageResponseDto.builder()
                .id(message.getId())
                .senderId(message.getSender().getId())
                .receiverId(message.getReceiver().getId())
                .content(message.getContent())
                .status(message.getStatus())
                .createdAt(message.getCreatedAt())
                .build();
    }
}
