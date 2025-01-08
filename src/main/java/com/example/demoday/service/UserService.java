package com.example.demoday.service;

import com.example.demoday.entity.User;
import com.example.demoday.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

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

    // 특정 사용자와 매칭된 상대방 조회
    public User findMatchedUser(Long userId) {
        // 모든 사용자 목록을 가져옴
        List<User> allUsers = userRepository.findAll();

        // 사용자 ID로 본인 데이터 필터링
        Optional<User> currentUser = userRepository.findById(userId);
        if (currentUser.isEmpty() || allUsers.size() <= 1) {
            // 유저가 없거나 매칭 가능한 다른 유저가 없는 경우
            return null;
        }

        // 현재 유저를 제외한 나머지 유저 목록
        allUsers.removeIf(user -> user.getId().equals(userId));

        // 랜덤으로 상대방 선택 (간단한 매칭 로직)
        Random random = new Random();
        int matchedIndex = random.nextInt(allUsers.size());

        return allUsers.get(matchedIndex);
    }

    public void deleteAllUsers() {
        userRepository.deleteAll(); // 데이터베이스의 모든 사용자 데이터 삭제
    }
}