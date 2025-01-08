package com.example.demoday.controller;

import com.example.demoday.entity.User;
import com.example.demoday.service.FileService;
import com.example.demoday.service.UserService;
import com.example.demoday.dto.MatchedUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final FileService fileService;

    @Autowired
    public UserController(UserService userService, FileService fileService) {
        this.userService = userService;
        this.fileService = fileService;
    }

    // 사용자 정보 저장
    @PostMapping
    public ResponseEntity<String> createUser(
            @RequestParam("name") String name,
            @RequestParam("age") int age,
            @RequestParam("residence") String residence,
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("instagramNickname") String instagramNickname,
            @RequestParam("personality") String personality,
            @RequestParam("idealType") String idealType,
            @RequestParam(value = "profilePhoto", required = false) MultipartFile profilePhoto
    ) {
        String profilePhotoPath = null;
        if (profilePhoto != null && !profilePhoto.isEmpty()) {
            try {
                profilePhotoPath = fileService.saveFile(profilePhoto);
            } catch (IOException e) {
                return ResponseEntity.badRequest().body("Failed to save profile photo: " + e.getMessage());
            }
        }

        User user = new User(name, age, residence, phoneNumber, instagramNickname, personality, idealType, profilePhotoPath);
        userService.saveUser(user);

        return ResponseEntity.ok("User information has been registered successfully!");
    }

    // 특정 사용자 정보 조회
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.findUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 모든 사용자 정보 조회
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // 특정 사용자에 매칭된 상대방의 프로필 사진 및 이름과 나이만 반환
    @GetMapping("/match/{userId}")
    public ResponseEntity<?> getMatchedUser(@PathVariable Long userId) {
        User matchedUser = userService.findMatchedUser(userId);

        if (matchedUser != null) {
            // 이름과 나이만 반환
            MatchedUserDTO matchedUserDTO = new MatchedUserDTO(
                    matchedUser.getName(),
                    matchedUser.getAge(),
                    null, null, null, null, null,
                    matchedUser.getProfilePhoto()
            );
            return ResponseEntity.ok(matchedUserDTO);
        } else {
            return ResponseEntity.status(404).body("No matched user found.");
        }
    }

    // 모든 사용자 데이터 삭제
    @DeleteMapping("/all")
    public ResponseEntity<String> deleteAllUsers() {
        userService.deleteAllUsers();
        return ResponseEntity.ok("All user data has been deleted.");
    }
}
