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
@Table(name="favorite_bin")
public class FavoriteBinEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.REMOVE, targetEntity = UserEntity.class)
    @JoinColumn(referencedColumnName = "id", name = "userid", nullable = false)
    UserEntity user; // 유저 id

    @Column(nullable=false, name="bin_id")
    private Long binId; // 휴지통 id

    @Column(nullable = false, length = 10, name="bin_type")
    private String binType; // 가로휴지통인지 재활용정거장인지
}
