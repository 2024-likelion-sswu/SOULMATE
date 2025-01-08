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
        testService.saveTestAnswers(testAnswerDTO); // 기존 오류 수정: 메서드 호출을 올바르게 변경
        return ResponseEntity.ok("Test answers submitted successfully!");
    }

    // 궁합도 계산
    @PostMapping("/calculate/{userId}")
    public ResponseEntity<String> calculateCompatibility(@PathVariable Long userId) {
        testService.calculateCompatibility(userId); // 사용자 ID로 궁합도 계산
        return ResponseEntity.ok("Compatibility calculation completed!");
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
