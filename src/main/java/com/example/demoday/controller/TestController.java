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

    // 궁합도 계산 및 결과 반환
    @GetMapping("/result")
    public ResponseEntity<TestResultDTO> calculateAndFetchResult() {
        TestResultDTO result = testService.calculateAndFetchResult(); // 궁합도 계산과 결과 반환
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }
}
