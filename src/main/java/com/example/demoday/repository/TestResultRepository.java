package com.example.demoday.repository;

import com.example.demoday.entity.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestResultRepository extends JpaRepository<TestResult, Long> {
    TestResult findByUserId(Long userId);
}
