package com.example.dssw.dto.ReportDeclaration;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UploadDeclarationDTO {
    private String address;
    private String detail_location;
    private String context;
    private String type;
}
