package com.capstone.trip.controller.api;

import com.capstone.trip.domain.accompany.Accompany;
import com.capstone.trip.domain.accompany.AccompanyRepository;
import com.capstone.trip.dto.realAccompany.RealAccompanySaveRequestDto;
import com.capstone.trip.service.AccompanyService;
import com.capstone.trip.service.RealAccompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Controller
public class RealAccompanyApiController {

    private final RealAccompanyService realAccompanyService;
    private final AccompanyRepository accompanyRepository;
    private final AccompanyService accompanyService;

    @PostMapping("/apply/realAccompany")
    @ResponseBody
    public void save(@RequestBody RealAccompanySaveRequestDto realAccompanySaveRequestDto) {
        Long accompanyId = realAccompanySaveRequestDto.getAccompanyId();
        Accompany accompany = accompanyRepository.findById(accompanyId).orElse(null);

        if (accompany != null) {
            // 사용자의 닉네임 가져오기
            String userNickname = accompany.getUserNickname();

            // 이제 userNickname을 필요한 대로 사용할 수 있습니다.
            System.out.println("사용자의 닉네임: " + userNickname);

            // RealAccompanyService의 save 메소드 호출
            realAccompanyService.save(accompanyId);
            accompanyService.deleteById(accompanyId);

        } else {
            // 주어진 ID에 대한 Accompany가 찾아지지 않은 경우 처리
            System.out.println("ID에 대한 Accompany를 찾을 수 없습니다: " + accompanyId);
        }
    }
}
