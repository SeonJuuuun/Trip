package com.capstone.trip.dto.star;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StarDto {

    private float selectedStar;
    private String reviewContent;
    private Long userId;
}
