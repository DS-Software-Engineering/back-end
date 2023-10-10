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
@Table(name="solve_board")
public class SolveBoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 45)
    private String title;

    @Column(nullable = false, length = 500)
    private String context;

    @ManyToOne(cascade = CascadeType.REMOVE, targetEntity = UserEntity.class)
    @JoinColumn(referencedColumnName = "id", name = "userid", nullable = false)
    UserEntity user; // 유저 id

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss", timezone = "Asia/Seoul")
    @Column(nullable = false)
    private LocalDateTime date; // 업로드 날짜 시간 분 초
}
