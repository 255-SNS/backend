package com.sns255.sns255.domain.essay.service;

import com.sns255.sns255.domain.board.entity.Board;
import com.sns255.sns255.domain.essay.dto.EssayRequest;
import com.sns255.sns255.domain.essay.dto.EssayResponse;
import com.sns255.sns255.domain.essay.entity.Essay;
import com.sns255.sns255.domain.essay.repository.EssayRepository;
import com.sns255.sns255.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class EssayService {

    private final EssayRepository essayRepository;

    public EssayResponse createEssay(EssayRequest request, Board board, User user) throws IOException {
        // Essay 객체 생성
        Essay essay = new Essay(
                board,
                user,
                request.getCompany(),
                request.getSchoolGrade(),
                request.getEnglishScore(),
                request.getAwards(),
                request.getCertificates(),
                request.getProjects(),
                request.getFileUrl1(),
                request.getFileUrl2()
        );

        // 데이터베이스에 저장
        Essay savedEssay = essayRepository.save(essay);

        // 응답 DTO로 변환
        return new EssayResponse(
                savedEssay.getId(),
                savedEssay.getCompany(),
                savedEssay.getSchoolGrade(),
                savedEssay.getEnglishScore(),
                savedEssay.getAwards(),
                savedEssay.getCertificates(),
                savedEssay.getProjects(),
                savedEssay.getFileUrl1(),
                savedEssay.getFileUrl2()
        );
    }
}
