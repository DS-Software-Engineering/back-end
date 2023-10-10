package com.example.dssw.controller;

import com.example.dssw.dto.ResponseDTO;
import com.example.dssw.dto.UserDTO;
import com.example.dssw.dto.tokenDTO;
import com.example.dssw.model.UserEntity;
import com.example.dssw.security.TokenProvider;
import com.example.dssw.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private TokenProvider tokenProvider;

    private PasswordEncoder passwordEncoder =new BCryptPasswordEncoder();

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO)
    {
        try{
            UserEntity user= UserEntity.builder()
                    .userid(userDTO.getUserid())
                    .nickname(userDTO.getNickname())
                    .password(passwordEncoder.encode(userDTO.getPassword()))
                    .phonenum(userDTO.getPhonenum())
                    .admin(false)
                    .reward(0)
                    .build();
            userService.create(user);
            ResponseDTO responseDTO= ResponseDTO.builder().status(200).Message("회원가입 성공").code("성공").build();
            return ResponseEntity.ok().body(responseDTO);
        }
        catch (Exception e){
            ResponseDTO responseDTO= ResponseDTO.builder().status(400).Message("회원가입 실패 - "+e.getMessage()).code("실패").build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }


    @PostMapping("/signin")
    public ResponseEntity<?>  authenticate(@RequestBody UserDTO userDTO)
    {
        UserEntity user=userService.getByCredentials(userDTO.getUserid(), userDTO.getPassword(),passwordEncoder);

        if(user != null){
            final String token=tokenProvider.create(user);
            final tokenDTO dto= tokenDTO.builder()
                    .token(token).build();
            List<tokenDTO> result=new ArrayList<>();
            result.add(dto);

            ResponseDTO responseDTO=ResponseDTO.<tokenDTO>builder().status(200).code("성공").Message("로그인 성공").data(result).build();
            return ResponseEntity.ok().body(responseDTO);
        }
        else{
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .status(400).code("실패").Message("로그인 실패")
                    .build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }



}
