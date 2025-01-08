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
        testService.saveTestAnswers(testAnswerDTO);
        return ResponseEntity.ok("Test answers submitted successfully!");
    }

    // 테스트 결과 조회 및 궁합도 계산
    @GetMapping("/result")
    public ResponseEntity<TestResultDTO> getTestResult() {
        TestResultDTO result = testService.calculateAndFetchResult();
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
