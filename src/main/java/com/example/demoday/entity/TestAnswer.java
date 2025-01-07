package com.example.demoday.entity;

import jakarta.persistence.*;

@Entity
public class TestAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String answer;

    // 질문 번호 추가
    private int questionNumber;

    // 기본 생성자
    public TestAnswer() {}

    public TestAnswer(User user, String answer, int questionNumber) {
        this.user = user;
        this.answer = answer;
        this.questionNumber = questionNumber;
    }

    // Getter and Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
