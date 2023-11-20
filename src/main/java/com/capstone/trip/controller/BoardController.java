package com.capstone.trip.controller;

import com.capstone.trip.domain.accompany.RealAccompany;
import com.capstone.trip.service.BoardService;
import com.capstone.trip.service.RealAccompanyService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;
    private final RealAccompanyService realAccompanyService;

    @GetMapping("/board/save")
    public String save() {
        return "layout/board/board-save";
    }

    @GetMapping("/board/{id}/update")
    public String update(@PathVariable Long id, Model model) {
        model.addAttribute("board", boardService.detail(id));
        return "layout/board/board-update";
    }

    /**
     * 글상세 페이지
     */
    @GetMapping("/board/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("board", boardService.detail(id));
        List<RealAccompany> realAccompanyList = realAccompanyService.findByBoardId(id);
        model.addAttribute("realAccompanyList", realAccompanyList);
        boardService.updateCount(id);
        return "layout/board/board-detail";
    }

    @GetMapping("/user/mypage/mypost/{id}")
    public String myDetail(@PathVariable Long id, Model model) {
        model.addAttribute("board", boardService.detail(id));
        boardService.updateCount(id);
        return "layout/board/board-detail";
    }
}
