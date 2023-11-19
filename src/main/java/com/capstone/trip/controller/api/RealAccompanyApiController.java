package com.capstone.trip.controller.api;

import com.capstone.trip.config.auth.PrincipalDetail;
import com.capstone.trip.dto.realAccompany.RealAccompanySaveRequestDto;
import com.capstone.trip.service.RealAccompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Controller
public class RealAccompanyApiController {

    private final RealAccompanyService realAccompanyService;

    @PostMapping("/apply/realAccompany")
    @ResponseBody
    public void save(@RequestBody RealAccompanySaveRequestDto realAccompanySaveRequestDto,
                     @AuthenticationPrincipal PrincipalDetail principalDetail) {
        Long userId = realAccompanySaveRequestDto.getUserId();
        Long boardId = realAccompanySaveRequestDto.getBoardId();
        String username = realAccompanySaveRequestDto.getUsername();
        String nickname = realAccompanySaveRequestDto.getNickname();
        String title = realAccompanySaveRequestDto.getTitle();
        System.out.println(userId);
        realAccompanyService.save(userId, boardId, username, nickname, title, principalDetail.getUser());
    }
}
