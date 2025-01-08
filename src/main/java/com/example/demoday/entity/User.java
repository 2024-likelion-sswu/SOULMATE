package com.example.demoday.entity;

import jakarta.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "residence")
    private String residence;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "instagram_nickname")
    private String instagramNickname;

    @Column(name = "personality")
    private String personality;

    @Column(name = "ideal_type")
    private String idealType;

    @Column(name = "profile_photo")
    private String profilePhoto;

    // 기본 생성자
    public User() {}

    // 모든 필드를 포함한 생성자
    public User(String name, int age, String residence, String phoneNumber, String instagramNickname, String personality, String idealType, String profilePhoto) {
        this.name = name;
        this.age = age;
        this.residence = residence;
        this.phoneNumber = phoneNumber;
        this.instagramNickname = instagramNickname;
        this.personality = personality;
        this.idealType = idealType;
        this.profilePhoto = profilePhoto;
    }

    // Getter and Setter
    public Long getId() {
        return id;
    }

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

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }
}
