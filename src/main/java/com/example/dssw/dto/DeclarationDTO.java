package com.example.dssw.dto;

import com.example.dssw.model.UserEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class DeclarationDTO {
    @Builder
    @Data
    public static class uploadDeclarationDTO{
        private String address;
        private String detail_location;
        private String context;
        private String type;
    }

    @Builder
    @Data
    public static class getDeclarationDTO{
        private Long user;
        private String address;
        private String detail_location;
        private String context;
        private String type;
        private LocalDateTime date;
        private String image_url;

    }
}
