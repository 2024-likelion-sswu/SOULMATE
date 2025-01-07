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

    public int getCompatibilityScore() {
        return compatibilityScore;
    }

    public MatchedUserDTO getMatchedUser() {
        return matchedUser;
    }

    public String getMessage() {
        return message;
    }
}
