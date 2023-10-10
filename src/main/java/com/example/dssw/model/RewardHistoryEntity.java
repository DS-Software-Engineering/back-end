package com.example.dssw.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="reward_history")
public class RewardHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private int reward; // 리워드 포인트

    @Column(nullable=false)
    private Long report_id; // 신고/건의 id

    @ManyToOne(cascade = CascadeType.REMOVE, targetEntity = UserEntity.class)
    @JoinColumn(referencedColumnName = "id", name = "userid", nullable = false)
    UserEntity user; // 유저 id

    @Column(nullable=false, length = 10)
    private String type;
}
