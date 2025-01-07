package com.example.demoday.controller;

import com.example.demoday.entity.User;
import com.example.demoday.service.UserService;
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
}
