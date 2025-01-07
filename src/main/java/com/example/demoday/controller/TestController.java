package com.example.demoday.controller;

import com.example.demoday.dto.TestAnswerDTO;
import com.example.demoday.dto.TestResultDTO;
import com.example.demoday.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {

    private final TestService testService;

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

    // 테스트 답안 제출
    @PostMapping
    public ResponseEntity<String> submitTestAnswers(@RequestBody TestAnswerDTO testAnswerDTO) {
        testService.calculateCompatibility(testAnswerDTO);
        return ResponseEntity.ok("Test answers submitted successfully!");
    }

    // 테스트 결과 및 상대방 정보 반환
    @GetMapping("/result/{userId}")
    public ResponseEntity<TestResultDTO> getTestResult(@PathVariable Long userId) {
        TestResultDTO result = testService.getTestResult(userId);
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}