package com.example.dssw.dto;

import com.example.dssw.model.ReportDeclarationEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportDeclarationDTO {

    private Long id;

    private String address;

    private String detail_location;

    private String image_url;

    private String type; // 신고 종류

    private LocalDateTime date; // 업로드 날짜 시간 분 초

    private String context;

    private Long userid;

    public ReportDeclarationDTO(ReportDeclarationEntity reportDeclarationEntity) {
       this.id = reportDeclarationEntity.getId();
       this.address = reportDeclarationEntity.getAddress();
       this.detail_location = reportDeclarationEntity.getDetail_location();
       this.image_url = reportDeclarationEntity.getImage_url();
       this.type=reportDeclarationEntity.getType();
       this.date=reportDeclarationEntity.getDate();
       this.context=reportDeclarationEntity.getContext();
    }
}
