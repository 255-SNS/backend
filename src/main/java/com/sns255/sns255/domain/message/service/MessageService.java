package com.sns255.sns255.domain.message.service;

import com.sns255.sns255.domain.message.dto.req.MessageRequestDto;
import com.sns255.sns255.domain.message.dto.res.MessageResponseDto;
import com.sns255.sns255.domain.message.entity.Message;
import com.sns255.sns255.domain.message.repository.MessageRepository;
import com.sns255.sns255.domain.user.entity.User;
import com.sns255.sns255.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    @Transactional
    public MessageResponseDto sendMessage(Long senderId, MessageRequestDto requestDto) {
        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new IllegalArgumentException("송신자를 찾을 수 없습니다."));
        User receiver = userRepository.findById(requestDto.getReceiverId())
                .orElseThrow(() -> new IllegalArgumentException("수신자를 찾을 수 없습니다."));

        Message message = new Message(sender, receiver, requestDto.getContent());
        Message savedMessage = messageRepository.save(message);

        return MessageResponseDto.fromEntity(savedMessage);
    }

    public List<MessageResponseDto> getReceivedMessages(Long receiverId) {
        User receiver = userRepository.findById(receiverId)
                .orElseThrow(() -> new IllegalArgumentException("수신자를 찾을 수 없습니다."));
        return messageRepository.findByReceiverOrderByCreatedAtDesc(receiver)
                .stream()
                .map(MessageResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

    public List<MessageResponseDto> getSentMessages(Long senderId) {
        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new IllegalArgumentException("송신자를 찾을 수 없습니다."));
        return messageRepository.findBySenderOrderByCreatedAtDesc(sender)
                .stream()
                .map(MessageResponseDto::fromEntity)
                .collect(Collectors.toList());
    }
}
