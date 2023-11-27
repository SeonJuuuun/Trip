package com.capstone.trip.service;

import com.capstone.trip.config.auth.PrincipalDetail;
import com.capstone.trip.domain.user.User;
import com.capstone.trip.domain.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 회원가입 로직
     */
    @Transactional
    public Long save(User user) {
        String hashPw = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(hashPw);
        return userRepository.save(user).getId();
    }

    /**
     * 회원수정 로직
     */
    @Transactional
    public Long update(User user, @AuthenticationPrincipal PrincipalDetail principalDetail) {
        User userEntity = userRepository.findById(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다. id=" + user.getId()));
        userEntity.update(bCryptPasswordEncoder.encode(user.getPassword()), user.getNickname());
        principalDetail.setUser(userEntity); //시큐리티 세션 정보 변경
        return userEntity.getId();
    }

    @Transactional(readOnly = true)
    public User findUser(String username) {
        User user = userRepository.findByUsername(username).orElseGet(User::new);
        return user;
    }

    @Transactional
    public boolean checkUsernameDuplicate(String username) {
        return userRepository.existsByUsername(username);
    }

    @Transactional
    public boolean checkEmailDuplicate(String email) {
        return userRepository.existsByEmail(email);
    }

    @Transactional
    public boolean checkNicknameDuplicate(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    @Transactional
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Transactional
    public void updateMannerScoreAndAddReview(Long targetUserId, float selectedStar, String reviewContent) {
        Optional<User> userOptional = userRepository.findById(targetUserId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // 매너점수 업데이트
            user.setMannerScore(BigDecimal.valueOf(selectedStar));

            // 리뷰 추가
            user.addReview(reviewContent);

            // userRepository.save(user); // 이 부분은 JPA에서 자동으로 처리됨
        } else {
            // 유저를 찾지 못한 경우 예외 처리
            throw new EntityNotFoundException("User not found with ID: " + targetUserId);
        }
    }
}
