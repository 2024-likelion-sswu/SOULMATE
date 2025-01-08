package com.example.demoday.dto;

public class TestResultDTO {
    private int compatibilityScore;
    private MatchedUserDTO matchedUser;
    private String message;

    public TestResultDTO(int compatibilityScore, MatchedUserDTO matchedUser, String message) {
        this.compatibilityScore = compatibilityScore;
        this.matchedUser = matchedUser;
        this.message = message;
    }

    // Getter and Setter
    public int getCompatibilityScore() {
        return compatibilityScore;
    }

    public void setCompatibilityScore(int compatibilityScore) {
        this.compatibilityScore = compatibilityScore;
    }

    public MatchedUserDTO getMatchedUser() {
        return matchedUser;
    }

    public void setMatchedUser(MatchedUserDTO matchedUser) {
        this.matchedUser = matchedUser;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
