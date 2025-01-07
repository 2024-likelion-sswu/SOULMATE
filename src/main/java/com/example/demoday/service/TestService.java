package com.example.demoday.service;

import com.example.demoday.dto.MatchedUserDTO;
import com.example.demoday.dto.TestAnswerDTO;
import com.example.demoday.dto.TestResultDTO;
import com.example.demoday.entity.TestAnswer;
import com.example.demoday.entity.TestResult;
import com.example.demoday.entity.User;
import com.example.demoday.repository.TestAnswerRepository;
import com.example.demoday.repository.TestResultRepository;
import com.example.demoday.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class TestService {

    private final UserRepository userRepository;
    private final TestResultRepository testResultRepository;
    private final TestAnswerRepository testAnswerRepository;

    @Autowired
    public TestService(UserRepository userRepository,
                       TestResultRepository testResultRepository,
                       TestAnswerRepository testAnswerRepository) {
        this.userRepository = userRepository;
        this.testResultRepository = testResultRepository;
        this.testAnswerRepository = testAnswerRepository;
    }

    // 테스트 답안 제출 및 궁합도 계산
    public void calculateCompatibility(TestAnswerDTO testAnswerDTO) {
        Long userId = testAnswerDTO.getUserId();
        List<Integer> userAnswers = testAnswerDTO.getAnswers();

        // 매칭된 상대방 찾기
        List<User> allUsers = userRepository.findAll();
        allUsers.removeIf(user -> user.getId().equals(userId)); // 본인을 제외한 사용자만 매칭 대상
        if (allUsers.isEmpty()) return; // 매칭 상대가 없으면 종료

        Random random = new Random();
        User matchedUser = allUsers.get(random.nextInt(allUsers.size()));

        // 상대방의 랜덤 답안 생성 (테스트용)
        List<Integer> matchedUserAnswers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            matchedUserAnswers.add(random.nextInt(2) + 1); // 1 또는 2
        }

        // 궁합도 계산 (일치하는 답변 수를 기반으로 계산)
        int matchingAnswers = 0;
        for (int i = 0; i < 10; i++) {
            if (userAnswers.get(i).equals(matchedUserAnswers.get(i))) {
                matchingAnswers++;
            }
        }
        int compatibilityScore = (matchingAnswers * 100) / 10; // 궁합도를 퍼센트로 계산

        // 테스트 결과 저장
        TestResult testResult = new TestResult(userId, matchedUser.getId(), compatibilityScore);

        // 디버깅용 로그 추가
        System.out.println("TestResult: " + testResult.getUserId() + ", " + testResult.getMatchedUserId() + ", " + testResult.getCompatibilityScore());

        testResultRepository.save(testResult);
    }

    // 테스트 결과 조회
    public TestResultDTO getTestResult(Long userId) {
        TestResult testResult = testResultRepository.findByUserId(userId);
        if (testResult == null) {
            return null; // 테스트 결과가 없으면 null 반환
        }

        User matchedUser = userRepository.findById(testResult.getMatchedUserId()).orElse(null);
        if (matchedUser == null) {
            return null; // 매칭된 사용자가 없으면 null 반환
        }

        if (testResult.getCompatibilityScore() >= 50) {
            // 궁합도 50% 이상일 경우 상대방 정보 공개
            MatchedUserDTO matchedUserDTO = new MatchedUserDTO(
                    matchedUser.getName(), matchedUser.getAge());
            return new TestResultDTO(
                    testResult.getCompatibilityScore(),
                    matchedUserDTO,
                    "Congratulations! Your soulmate is revealed!"
            );
        } else {
            // 궁합도 50% 미만일 경우 상대방 정보 비공개
            return new TestResultDTO(
                    testResult.getCompatibilityScore(),
                    null,
                    "Sorry, compatibility is below 50%. No details revealed."
            );
        }
    }
}
