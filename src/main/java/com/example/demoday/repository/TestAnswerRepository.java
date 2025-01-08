package com.example.demoday.repository;

import com.example.demoday.entity.TestAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestAnswerRepository extends JpaRepository<TestAnswer, Long> {
    List<TestAnswer> findByUserId(Long userId); // 단일 결과에서 다중 결과로 변경
}
