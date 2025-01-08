package com.example.demoday.controller;

import com.example.demoday.entity.User;
import com.example.demoday.service.UserService;
import com.example.demoday.dto.MatchedUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 사용자 정보 저장
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user) {
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

    // 특정 사용자에 매칭된 상대방의 이름과 나이만 반환
    @GetMapping("/match/{userId}")
    public ResponseEntity<?> getMatchedUser(@PathVariable Long userId) {
        User matchedUser = userService.findMatchedUser(userId);

        if (matchedUser != null) {
            MatchedUserDTO matchedUserDTO = new MatchedUserDTO(
                    matchedUser.getName(),
                    matchedUser.getAge(),
                    matchedUser.getResidence(),
                    matchedUser.getPhoneNumber(),
                    matchedUser.getInstagramNickname(),
                    matchedUser.getPersonality(),
                    matchedUser.getIdealType(),
                    matchedUser.getProfileImage() // 프로필 이미지 포함
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