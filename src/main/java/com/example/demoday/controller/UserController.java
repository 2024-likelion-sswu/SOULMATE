package com.example.demoday.controller;

import com.example.demoday.entity.TestAnswer;
import com.example.demoday.entity.User;
import com.example.demoday.service.UserService;
import com.example.demoday.dto.MatchedUserDTO;
import com.example.demoday.dto.TestAnswerDTO;
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
        System.out.println("Received User: " + user);
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
            // 이름과 나이만 반환
            return ResponseEntity.ok(new MatchedUserDTO(matchedUser.getName(), matchedUser.getAge()));
        } else {
            return ResponseEntity.status(404).body("No matched user found.");
        }
    }

    // 테스트 답안 저장
    @PostMapping("/test-answers")
    public ResponseEntity<String> submitTestAnswers(@RequestBody TestAnswerDTO testAnswerDTO) {
        boolean result = userService.saveTestAnswers(testAnswerDTO);
        if (result) {
            return ResponseEntity.ok("Test answers have been saved successfully!");
        } else {
            return ResponseEntity.status(400).body("Failed to save test answers.");
        }
    }

    // 특정 사용자의 답변 조회
    @GetMapping("/test-answers/{userId}")
    public ResponseEntity<List<TestAnswer>> getTestAnswers(@PathVariable Long userId) {
        List<TestAnswer> answers = userService.getTestAnswersByUserId(userId);
        if (answers != null && !answers.isEmpty()) {
            return ResponseEntity.ok(answers);
        } else {
            return ResponseEntity.status(404).body(null); // 답변이 없으면 404 반환
        }
    }

    // 모든 사용자 데이터 삭제
    @DeleteMapping("/all")
    public ResponseEntity<String> deleteAllUsers() {
        userService.deleteAllUsers(); // UserService에 삭제 로직 추가
        return ResponseEntity.ok("All user data has been deleted.");
    }
}
