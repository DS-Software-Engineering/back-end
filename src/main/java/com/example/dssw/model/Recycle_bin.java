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
@Table(name="recycle_bin")
public class Recycle_bin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String village_name;

    @Column(nullable=false)
    private String address;

    @Column(nullable=false)
    private String detail_location;

    @Column(nullable=false)
    private String center_phone_num;

    @Column(nullable = false)
    private float latitude;

    @Column(nullable = false)
    private float longtitude;



}
