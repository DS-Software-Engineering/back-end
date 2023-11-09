package com.example.dssw.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteBinDTO {
    private Long binId;
    private double latitude;

    private double longtitude;



    // 재활용 정거장인지 가로휴지통인지
    private String BinType;

    private String address;

    private String detail_location;


}
