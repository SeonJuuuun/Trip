package com.capstone.trip.controller;

import com.capstone.trip.config.auth.PrincipalDetail;
import com.capstone.trip.domain.accompany.Accompany;
import com.capstone.trip.domain.board.Board;
import com.capstone.trip.service.AccompanyService;
import com.capstone.trip.service.BoardService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class myPageController {

    @Autowired
    private BoardService boardService;
    @Autowired
    private AccompanyService accompanyService;

    @GetMapping("/user/mypage/mypost")
    public String mypost(Model model,
                         @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                         @AuthenticationPrincipal PrincipalDetail principalDetail) {
        Page<Board> boards = boardService.findByUser_Id(principalDetail.getId(), pageable);
        int startPage = Math.max(1, boards.getPageable().getPageNumber() - 4);
        int endPage = Math.min(boards.getTotalPages(), boards.getPageable().getPageNumber() + 4);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("boards", boards);
        return "layout/user/mypage/mypage-mypost";

    }

    @GetMapping("/user/mypage/request")
    public String accompanyIndex(Model model, @AuthenticationPrincipal PrincipalDetail principalDetail) {
        List<Accompany> accompany = accompanyService.findByUser_Id(
                principalDetail.getId());
        model.addAttribute("accompany", accompany);
        return "layout/user/mypage/mypage-request";
    }
}
