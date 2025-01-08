package com.example.demoday.entity;

import jakarta.persistence.*;

@Entity
public class TestResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "matched_user_id")
    private Long matchedUserId;

    @Column(name = "compatibility_score")
    private int compatibilityScore;

    public TestResult() {}

    public TestResult(Long userId, Long matchedUserId, int compatibilityScore) {
        this.userId = userId;
        this.matchedUserId = matchedUserId;
        this.compatibilityScore = compatibilityScore;
    }

    // Getter methods
    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getMatchedUserId() {
        return matchedUserId;
    }

    public int getCompatibilityScore() {
        return compatibilityScore;
    }

    // Setter methods
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setMatchedUserId(Long matchedUserId) {
        this.matchedUserId = matchedUserId;
    }

    public void setCompatibilityScore(int compatibilityScore) {
        this.compatibilityScore = compatibilityScore;
    }
}
