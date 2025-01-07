package com.example.demoday.service;

import com.example.demoday.entity.User;
import com.example.demoday.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 모든 사용자 데이터 조회
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // 사용자 데이터 저장
    public void saveUser(User user) {
        userRepository.save(user);
    }

    // 특정 id에 대한 사용자 조회
    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
