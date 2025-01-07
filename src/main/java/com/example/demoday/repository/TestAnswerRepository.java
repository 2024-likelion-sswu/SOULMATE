package com.example.demoday.repository;

import com.example.demoday.entity.TestAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestAnswerRepository extends JpaRepository<TestAnswer, Long> {
    TestAnswer findByUserId(Long userId);
}
