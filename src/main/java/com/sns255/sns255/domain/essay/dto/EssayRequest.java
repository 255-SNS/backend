package com.sns255.sns255.domain.essay.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EssayRequest {
    private String company;
    private String schoolGrade;
    private String englishScore;
    private String awards;
    private String certificates;
    private String projects;
    private String fileUrl1;
    private String fileUrl2;
}
