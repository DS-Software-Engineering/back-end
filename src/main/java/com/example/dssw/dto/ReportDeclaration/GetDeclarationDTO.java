package com.example.dssw.dto.ReportDeclaration;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetDeclarationDTO {
    private Long user;
    private String address;
    private String detail_location;
    private String context;
    private String type;
    private LocalDateTime date;
    private String image_url;
}
