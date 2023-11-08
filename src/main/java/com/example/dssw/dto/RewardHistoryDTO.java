package com.example.dssw.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RewardHistoryDTO {
    private Long id;

    private String address;

    private String detail_location;

    private LocalDateTime date; // 업로드 날짜 시간 분 초

    private Long report_id;

    private int reward; // 리워드 포인트

}
