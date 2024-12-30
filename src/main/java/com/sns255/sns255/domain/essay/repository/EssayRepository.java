package com.sns255.sns255.domain.essay.repository;

import com.sns255.sns255.domain.essay.entity.Essay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EssayRepository extends JpaRepository<Essay, Long> {
    // 추가적인 쿼리가 필요하면 여기에서 정의 가능
}
