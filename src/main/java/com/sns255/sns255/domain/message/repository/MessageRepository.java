package com.sns255.sns255.domain.message.repository;

import com.sns255.sns255.domain.message.entity.Message;
import com.sns255.sns255.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByReceiverOrderByCreatedAtDesc(User receiver); // 받은 쪽지
    List<Message> findBySenderOrderByCreatedAtDesc(User sender); // 보낸 쪽지
}