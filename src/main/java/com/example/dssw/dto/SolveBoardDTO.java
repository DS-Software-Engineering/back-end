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
public class SolveBoardDTO {

    private Long id;
    private String title;
    private String context;
    private Long userId;
    private LocalDateTime date;

}
