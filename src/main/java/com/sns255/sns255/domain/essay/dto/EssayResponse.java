package com.sns255.sns255.domain.essay.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EssayResponse {
    private Long id;
    private String company;
    private String schoolGrade;
    private String englishScore;
    private String awards;
    private String certificates;
    private String projects;
    private String fileUrl1;
    private String fileUrl2;
}
