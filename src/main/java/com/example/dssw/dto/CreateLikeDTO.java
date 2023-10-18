package com.example.dssw.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateLikeDTO {
    private String userId;
    private Long binId;
    private String binType;
}
