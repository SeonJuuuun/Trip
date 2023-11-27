package com.capstone.trip.controller;

import com.capstone.trip.domain.user.User;
import com.capstone.trip.service.ReviewService;
import com.capstone.trip.service.UserService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final UserService userService;

    @GetMapping("/review/save")
    public String save() {
        return "layout/review/review-save";
    }

    @GetMapping("/review/{id}/update")
    public String update(@PathVariable Long id, Model model) {
        model.addAttribute("review", reviewService.detail(id));
        return "layout/review/review-update";
    }

    @GetMapping("/review/star/{id}")
    public String star(@PathVariable Long id, Model model) {
        Optional<User> userOptional = userService.getUserById(id);
        User user = userOptional.get();
        model.addAttribute("user", user);
        return "layout/review/review-star";
    }

    @GetMapping("/review/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("review", reviewService.detail(id));
        reviewService.updateCount(id);
        return "layout/review/review-detail";
    }
}
