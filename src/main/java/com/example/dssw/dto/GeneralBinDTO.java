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
public class GeneralBinDTO {
    private String street_name;

    private String address;

    private String detail_location;

    private boolean type_general;

    private boolean type_cb;

    private boolean type_recycle;

    private boolean type_drink;
    private String shape;

    private boolean favorite;

    private double latitude;

    private double longtitude;

}
