package com.example.demoday.dto;

public class MatchedUserDTO {
    private String name;
    private int age;
    private String residence;
    private String phoneNumber;
    private String instagramNickname;
    private String personality;
    private String idealType;
    private String profileImage; // 프로필 이미지 추가

    public MatchedUserDTO(String name, int age, String residence, String phoneNumber,
                          String instagramNickname, String personality,
                          String idealType, String profileImage) {
        this.name = name;
        this.age = age;
        this.residence = residence;
        this.phoneNumber = phoneNumber;
        this.instagramNickname = instagramNickname;
        this.personality = personality;
        this.idealType = idealType;
        this.profileImage = profileImage; // 추가
    }

    // Getter and Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getInstagramNickname() {
        return instagramNickname;
    }

    public void setInstagramNickname(String instagramNickname) {
        this.instagramNickname = instagramNickname;
    }

    public String getPersonality() {
        return personality;
    }

    public void setPersonality(String personality) {
        this.personality = personality;
    }

    public String getIdealType() {
        return idealType;
    }

    public void setIdealType(String idealType) {
        this.idealType = idealType;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}
