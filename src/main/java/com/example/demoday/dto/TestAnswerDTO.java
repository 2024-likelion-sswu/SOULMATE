package com.example.demoday.dto;

import java.util.List;

public class TestAnswerDTO {
    private Long userId;
    private List<Integer> answers;

    public TestAnswerDTO() {}

    public TestAnswerDTO(Long userId, List<Integer> answers) {
        this.userId = userId;
        this.answers = answers;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Integer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Integer> answers) {
        this.answers = answers;
    }
}
