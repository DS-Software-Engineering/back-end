package com.example.dssw.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserDTO {

    private String userid;

    private String nickname;

    private String password;

    private String phonenum;

}
