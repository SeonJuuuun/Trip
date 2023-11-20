package com.capstone.trip.dto.accompany;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccompanySaveRequestDto {

    private Long userId;
    private Long boardId;
    private String title;
    private String username;
    private String nickname;

}
