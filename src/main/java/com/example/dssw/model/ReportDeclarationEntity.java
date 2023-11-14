package com.example.dssw.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="report_declaration")
public class ReportDeclarationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.REMOVE, targetEntity = UserEntity.class)
    @JoinColumn(referencedColumnName = "id", name = "userid", nullable = false)
    UserEntity user; // 유저 id

    @Column(nullable=false, length = 45)
    private String address;

    @Column(nullable=false, length = 45)
    private String detail_location;

    @Column(nullable=false, length = 200)
    private String image_url;

    @Column(columnDefinition = "TEXT")
    private String context;

    @Column(nullable=false, length = 10)
    private String type; // 신고 종류

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss", timezone = "Asia/Seoul")
    @CreatedDate
    private LocalDateTime date; // 업로드 날짜 시간 분 초
}
