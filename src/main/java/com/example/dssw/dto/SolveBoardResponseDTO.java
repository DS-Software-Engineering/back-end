package com.example.dssw.dto;

import com.example.dssw.model.SolveBoardImagesEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SolveBoardResponseDTO {

    private Long id;

    private String title;

    private String context;

    private String userNickname;

    private LocalDateTime date;

    private List<String> image_url;
}
