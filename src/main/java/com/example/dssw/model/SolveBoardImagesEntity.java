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
@Table(name="solve_board_images")
public class SolveBoardImagesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length = 200)
    private String image_url;

    @ManyToOne(cascade = CascadeType.REMOVE, targetEntity = SolveBoardEntity.class)
    @JoinColumn(referencedColumnName = "id", name = "board_id", nullable = false)
    SolveBoardEntity solveBoard; // 게시글 id
}
