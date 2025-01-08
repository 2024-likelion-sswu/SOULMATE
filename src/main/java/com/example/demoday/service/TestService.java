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

    // 궁합도 계산 및 결과 저장
    public void calculateCompatibility(Long userId) {
        // 사용자 답안 가져오기
        List<TestAnswer> userAnswersList = testAnswerRepository.findByUserId(userId);
        if (userAnswersList.isEmpty()) return;

        TestAnswer userAnswer = userAnswersList.get(0);

        // 매칭 상대방 찾기
        List<User> allUsers = userRepository.findAll();
        allUsers.removeIf(user -> user.getId().equals(userId)); // 본인 제외
        if (allUsers.isEmpty()) return;

        User matchedUser = allUsers.get(0); // 첫 번째 상대방 매칭
        List<TestAnswer> matchedAnswersList = testAnswerRepository.findByUserId(matchedUser.getId());
        if (matchedAnswersList.isEmpty()) return;

        TestAnswer matchedUserAnswer = matchedAnswersList.get(0);

        // 궁합도 계산
        List<Integer> userAnswers = userAnswer.getAnswers();
        List<Integer> matchedUserAnswers = matchedUserAnswer.getAnswers();
        int matchingAnswers = 0;

        for (int i = 0; i < userAnswers.size(); i++) {
            if (userAnswers.get(i).equals(matchedUserAnswers.get(i))) {
                matchingAnswers++;
            }
        }

        int compatibilityScore = (matchingAnswers * 100) / userAnswers.size();

        // 기존 TestResult 데이터 삭제 또는 업데이트
        TestResult existingResult = testResultRepository.findByUserId(userId);
        if (existingResult != null) {
            existingResult.setMatchedUserId(matchedUser.getId());
            existingResult.setCompatibilityScore(compatibilityScore);
            testResultRepository.save(existingResult); // 업데이트
        } else {
            TestResult newResult = new TestResult(userId, matchedUser.getId(), compatibilityScore);
            testResultRepository.save(newResult); // 새로 저장
        }
    }

    // 테스트 결과 조회
    public TestResultDTO getTestResult(Long userId) {
        TestResult testResult = testResultRepository.findByUserId(userId);
        if (testResult == null) {
            return null;
        }

        User matchedUser = userRepository.findById(testResult.getMatchedUserId()).orElse(null);
        if (matchedUser == null) {
            return null;
        }

        if (testResult.getCompatibilityScore() >= 50) {
            MatchedUserDTO matchedUserDTO = new MatchedUserDTO(
                    matchedUser.getName(),
                    matchedUser.getAge(),
                    matchedUser.getResidence(),
                    matchedUser.getPhoneNumber(),
                    matchedUser.getInstagramNickname(),
                    matchedUser.getPersonality(),
                    matchedUser.getIdealType()
            );
            return new TestResultDTO(
                    testResult.getCompatibilityScore(),
                    matchedUserDTO,
                    "Congratulations! Your soulmate is revealed!"
            );
        } else {
            return new TestResultDTO(
                    testResult.getCompatibilityScore(),
                    null,
                    "Sorry, compatibility is below 50%. No details revealed."
            );
        }
    }
}
