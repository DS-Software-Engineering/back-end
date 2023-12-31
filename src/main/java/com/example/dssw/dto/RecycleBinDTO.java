package com.example.dssw.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecycleBinDTO {

    private String village_name;

    private String address;

    private String detail_location;

    private String center_phone_num;

    private boolean favorite;
    private double latitude;

    private double longtitude;

}
