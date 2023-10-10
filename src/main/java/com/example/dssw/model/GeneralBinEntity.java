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
@Table(name="general_bin")
public class GeneralBinEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String street_name;

    @Column(nullable=false)
    private String address;

    @Column(nullable=false)
    private String detail_location;

    @Column(name="open_status", columnDefinition = "TINYINT", length=1)
    private boolean type_general;

    @Column(name="open_status", columnDefinition = "TINYINT", length=1)
    private boolean type_cb;

    @Column(name="open_status", columnDefinition = "TINYINT", length=1)
    private boolean type_recycle;

    @Column(name="open_status", columnDefinition = "TINYINT", length=1)
    private boolean type_drink;

    @Column(nullable=false)
    private String shape;

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longtitude;

}
