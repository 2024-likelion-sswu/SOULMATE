package com.example.demoday.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class TestAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @ElementCollection
    @CollectionTable(name = "test_answers", joinColumns = @JoinColumn(name = "test_id"))
    @Column(name = "answer")
    private List<Integer> answers;

    public TestAnswer() {}

    public TestAnswer(Long userId, List<Integer> answers) {
        this.userId = userId;
        this.answers = answers;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public List<Integer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Integer> answers) {
        this.answers = answers;
    }
}
