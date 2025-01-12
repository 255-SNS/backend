package com.sns255.sns255.domain.message.entity;

import com.sns255.sns255.domain.user.entity.User;
import com.sns255.sns255.global.config.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Message extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender; // 송신자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id", nullable = false)
    private User receiver; // 수신자

    @Column(nullable = false, length = 1000)
    private String content; // 메시지 내용

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MessageStatus status = MessageStatus.UNREAD; // 읽음 상태 기본값

    public Message(User sender, User receiver, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.status = MessageStatus.UNREAD;
    }

    public void markAsRead() {
        this.status = MessageStatus.READ;
    }
}
