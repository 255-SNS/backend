package com.sns255.sns255.domain.message.controller;

import com.sns255.sns255.domain.message.dto.req.MessageRequestDto;
import com.sns255.sns255.domain.message.dto.res.MessageResponseDto;
import com.sns255.sns255.domain.message.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/send/{senderId}")
    public ResponseEntity<MessageResponseDto> sendMessage(@PathVariable Long senderId,
                                                          @RequestBody MessageRequestDto requestDto) {
        MessageResponseDto response = messageService.sendMessage(senderId, requestDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/received/{receiverId}")
    public ResponseEntity<List<MessageResponseDto>> getReceivedMessages(@PathVariable Long receiverId) {
        return ResponseEntity.ok(messageService.getReceivedMessages(receiverId));
    }

    @GetMapping("/sent/{senderId}")
    public ResponseEntity<List<MessageResponseDto>> getSentMessages(@PathVariable Long senderId) {
        return ResponseEntity.ok(messageService.getSentMessages(senderId));
    }
}
