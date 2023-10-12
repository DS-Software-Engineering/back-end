package com.example.dssw.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MapDTO {
    private Long id;
    private double latitude;

    private double longtitude;

    // 재활용 정거장인지 가로휴지통인지
    private String BinType;
}
