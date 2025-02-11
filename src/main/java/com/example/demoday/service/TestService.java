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

import java.util.List;

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

    // 테스트 답안 저장
    public void saveTestAnswers(TestAnswerDTO testAnswerDTO) {
        Long userId = testAnswerDTO.getUserId();
        List<Integer> userAnswers = testAnswerDTO.getAnswers();

        // 기존 답안을 삭제
        List<TestAnswer> existingAnswers = testAnswerRepository.findByUserId(userId);
        if (!existingAnswers.isEmpty()) {
            testAnswerRepository.deleteAll(existingAnswers);
        }

        // 새로운 답안 저장
        TestAnswer newAnswer = new TestAnswer(userId, userAnswers);
        testAnswerRepository.save(newAnswer);
    }

    // 궁합도 계산 및 결과 반환
    public TestResultDTO calculateAndFetchResult() {
        // 모든 사용자의 답안 가져오기
        List<TestAnswer> allAnswers = testAnswerRepository.findAll();

        if (allAnswers.size() != 2) {
            System.out.println("Error: There must be exactly 2 users to calculate compatibility.");
            return null;
        }

        // 사용자 1과 사용자 2 가져오기
        TestAnswer user1Answer = allAnswers.get(0);
        TestAnswer user2Answer = allAnswers.get(1);

        Long user1Id = user1Answer.getUserId();
        Long user2Id = user2Answer.getUserId();

        List<Integer> user1Answers = user1Answer.getAnswers();
        List<Integer> user2Answers = user2Answer.getAnswers();

        // 궁합도 계산
        int matchingAnswers = 0;
        for (int i = 0; i < user1Answers.size(); i++) {
            if (user1Answers.get(i).equals(user2Answers.get(i))) {
                matchingAnswers++;
            }
        }

        int compatibilityScore = (matchingAnswers * 100) / user1Answers.size();

        // TestResult 업데이트 또는 새로 저장 (사용자 1)
        TestResult user1Result = testResultRepository.findByUserId(user1Id);
        if (user1Result == null) {
            user1Result = new TestResult(user1Id, user2Id, compatibilityScore);
        } else {
            user1Result.setMatchedUserId(user2Id);
            user1Result.setCompatibilityScore(compatibilityScore);
        }
        testResultRepository.save(user1Result);

        // TestResult 업데이트 또는 새로 저장 (사용자 2)
        TestResult user2Result = testResultRepository.findByUserId(user2Id);
        if (user2Result == null) {
            user2Result = new TestResult(user2Id, user1Id, compatibilityScore);
        } else {
            user2Result.setMatchedUserId(user1Id);
            user2Result.setCompatibilityScore(compatibilityScore);
        }
        testResultRepository.save(user2Result);

        // 상대방 정보 반환 (사용자 1 기준)
        User matchedUser = userRepository.findById(user2Id).orElse(null);
        if (matchedUser == null) {
            return null;
        }

        if (compatibilityScore >= 50) {
            // MatchedUserDTO의 올바른 생성자 호출
            MatchedUserDTO matchedUserDTO = new MatchedUserDTO(
                    matchedUser.getName(),
                    matchedUser.getAge(),
                    matchedUser.getResidence(),
                    matchedUser.getPhoneNumber(),
                    matchedUser.getInstagramNickname(),
                    matchedUser.getPersonality(),
                    matchedUser.getIdealType(),
                    matchedUser.getProfileImage() // 프로필 이미지 포함
            );
            return new TestResultDTO(
                    compatibilityScore,
                    matchedUserDTO,
                    "Congratulations! Your soulmate is revealed!"
            );
        } else {
            return new TestResultDTO(
                    compatibilityScore,
                    null,
                    "Sorry, compatibility is below 50%. No details revealed."
            );
        }
    }
}
