package com.example.demoday.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "test_answer") // 엔티티 테이블 이름 명시
public class TestAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @ElementCollection
    @CollectionTable(
            name = "test_answer_details", // 컬렉션 테이블 이름 명시
            joinColumns = @JoinColumn(name = "test_id")
    )
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

