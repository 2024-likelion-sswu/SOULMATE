package com.example.demoday.service;

import com.example.demoday.entity.TestAnswer;
import com.example.demoday.repository.TestAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestAnswerService {

    private final TestAnswerRepository testAnswerRepository;

    @Autowired
    public TestAnswerService(TestAnswerRepository testAnswerRepository) {
        this.testAnswerRepository = testAnswerRepository;
    }

    // 답변 저장
    public void saveAnswer(TestAnswer testAnswer) {
        testAnswerRepository.save(testAnswer);
    }

    // 특정 사용자 답변 조회
    public List<TestAnswer> getAnswersByUserId(Long userId) {
        return testAnswerRepository.findByUserId(userId);
    }
}
