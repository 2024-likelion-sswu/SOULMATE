package com.example.demoday.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TestAnswerDTO {
    private Long userId;
    private List<AnswerDTO> answers;

    public TestAnswerDTO() {
    }

    public TestAnswerDTO(Long userId, List<AnswerDTO> answers) {
        this.userId = userId;
        this.answers = answers;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<AnswerDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerDTO> answers) {
        this.answers = answers;
    }

    public static class AnswerDTO {
        private String answer;
        private int questionNumber;

        @JsonCreator
        public AnswerDTO(@JsonProperty("answer") String answer,
                         @JsonProperty("questionNumber") int questionNumber) {
            this.answer = answer;
            this.questionNumber = questionNumber;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public int getQuestionNumber() {
            return questionNumber;
        }

        public void setQuestionNumber(int questionNumber) {
            this.questionNumber = questionNumber;
        }
    }
}
