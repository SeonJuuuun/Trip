package com.capstone.trip.controller;

import com.capstone.trip.domain.board.Board;
import com.capstone.trip.service.RealAccompanyService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class RealAccompanyController {

    private final RealAccompanyService realAccompanyService;

    @GetMapping("/user-accompany-history/{id}")
    public String userHistory(@PathVariable Long id, Model model) {
        List<Board> userAccompanyHistory = realAccompanyService.getUserAccompanyHistory(id);

        // 모델에 데이터 추가
        model.addAttribute("userAccompanyHistory", userAccompanyHistory);
        model.addAttribute("id", id);

        // 해당하는 Thymeleaf 템플릿을 사용하는 HTML 페이지 반환
        return "layout/realAccompany/user-history";
    }
}
