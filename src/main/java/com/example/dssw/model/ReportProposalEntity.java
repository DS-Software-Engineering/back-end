package com.example.dssw.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="report_proposal")
public class ReportProposalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.REMOVE, targetEntity = UserEntity.class)
    @JoinColumn(referencedColumnName = "id", name = "userid", nullable = false)
    UserEntity user; // 유저 id

    @Column(nullable=false, length = 45)
    private String title;

    @Column(nullable=false, columnDefinition = "TEXT")
    private String context;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss", timezone = "Asia/Seoul")
    @Column(nullable = false)
    private LocalDateTime date; // 업로드 날짜 시간 분 초
}
