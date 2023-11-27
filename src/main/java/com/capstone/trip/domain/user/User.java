package com.capstone.trip.domain.user;

import com.capstone.trip.domain.BaseTimeEntity;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //sequence, auto_increment

    @Column(nullable = false, length = 50, unique = true)
    private String username; //아이디

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 30)
    private String email;

    @Column(nullable = false)
    private String nickname; //닉네임

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column
    private String provider;

    @Column
    private String providerId;

    @Column
    private String gender;

    @Column
    private String date;

    @Column
    private String type;

    @Column
    private BigDecimal mannerScore;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "user_reviews", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "review")
    private List<String> reviews = new ArrayList<>();

    @ElementCollection
    private List<BigDecimal> mannerScoreHistory = new ArrayList<>();

    public void setMannerScore(BigDecimal newMannerScore) {
        // 새로운 값을 추가
        this.mannerScoreHistory.add(newMannerScore);

        // 새로운 평균 계산
        BigDecimal sum = BigDecimal.ZERO;
        for (BigDecimal score : mannerScoreHistory) {
            sum = sum.add(score);
        }

        this.mannerScore = sum.divide(BigDecimal.valueOf(mannerScoreHistory.size()), 2, RoundingMode.HALF_UP);
    }

    public void addReview(String review) {
        this.reviews.add(review);
    }

    /**
     * 비밀번호 암호화 메소드
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 권한 메소드
     */
    public String getRoleKey() {
        return this.role.getKey();
    }

    /**
     * 회원수정 메소드
     */
    public void update(String password, String nickname) {
        this.password = password;
        this.nickname = nickname;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

