package com.capstone.trip.controller.api;

import com.capstone.trip.dto.star.StarDto;
import com.capstone.trip.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class StarApiController {

    private final UserService userService;

    @PostMapping("/api/star")
    public void acceptScope(@RequestBody StarDto starDto) {
        float selectedStar = starDto.getSelectedStar();
        String reviewContent = starDto.getReviewContent();
        Long targetUserId = starDto.getUserId();
        
        userService.updateMannerScoreAndAddReview(targetUserId, selectedStar, reviewContent);
    }
}
