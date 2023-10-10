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
public class General_bin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String street_name;

    @Column(nullable=false)
    private String address;

    @Column(nullable=false)
    private String detail_location;

    @Column(nullable = false)
    private boolean type_general;

    @Column(nullable = false)
    private boolean type_cb;

    @Column(nullable = false)
    private boolean type_recycle;

    @Column(nullable = false)
    private boolean type_drink;

    @Column(nullable = false)
    private float latitude;

    @Column(nullable = false)
    private float longtitude;


}
