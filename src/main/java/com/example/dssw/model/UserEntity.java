package com.example.dssw.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
//import javax.*;
//import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false,length = 10)
    private String userid;

    @Column(nullable=false,length = 10)
    private String nickname; // 아이디와 같은 기능

    @Column(nullable=false)
    private String password;

    @Column(nullable=false)
    private String phonenum;

    @Column(nullable = false)
    private int reward;

    @Column(nullable = false)
    private boolean admin;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FavoriteBinEntity> favorites;

}
