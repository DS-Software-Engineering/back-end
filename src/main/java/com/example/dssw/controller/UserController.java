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
            ResponseDTO responseDTO= ResponseDTO.builder().status(200).Message("회원가입 성공").success(true).build();
            return ResponseEntity.ok().body(responseDTO);
        }
        catch (Exception e){
            ResponseDTO responseDTO= ResponseDTO.builder().status(400).Message("회원가입 실패 - "+e.getMessage()).success(false).build();
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

            ResponseDTO responseDTO=ResponseDTO.<tokenDTO>builder().status(200).success(true).Message("로그인 성공").data(result).build();
            return ResponseEntity.ok().body(responseDTO);
        }
        else{
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .status(400).success(false).Message("로그인 실패")
                    .build();
            return ResponseEntity.ok().body(responseDTO);
        }
    }

    @PostMapping("/findId")
    public ResponseEntity<ResponseDTO>  findIdByPhonenum(@RequestBody UserDTO userDTO){
        try{
            String userid=userService.findIdByPhoneNum(userDTO.getPhonenum());
            List<String> result=new ArrayList<>();
            result.add(userid);
            ResponseDTO responseDTO=ResponseDTO.<String>builder().status(200).success(true).data(result).build();
            return ResponseEntity.ok().body(responseDTO);
        }catch (Exception e){
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .status(400).success(false).Message(e.getMessage())
                    .build();
            return ResponseEntity.ok().body(responseDTO);
        }

    }
    @PostMapping("/findPassword")
    public ResponseEntity<ResponseDTO>  findPasswordByPhonenum(@RequestBody UserDTO userDTO){
        try{
            boolean isSuccess = userService.findPasswordByPhonenumAndUserid(userDTO.getPhonenum(),userDTO.getUserid());
            List<Boolean> result=new ArrayList<>();
            result.add(isSuccess);
            ResponseDTO responseDTO=ResponseDTO.<Boolean>builder().status(200).success(true).data(result).build();
            return ResponseEntity.ok().body(responseDTO);
        }catch (Exception e){
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .status(400).success(false).Message(e.getMessage())
                    .build();
            return ResponseEntity.ok().body(responseDTO);
        }
    }

    @PatchMapping("/changePassword")
    public ResponseEntity<ResponseDTO>  changePassword(@RequestBody UserDTO userDTO){
        try{
            boolean isSuccess=userService.changePassword(userDTO.getPhonenum(),userDTO.getUserid(),passwordEncoder.encode(userDTO.getPassword()));
            List<Boolean> result=new ArrayList<>();
            result.add(isSuccess);
            ResponseDTO responseDTO=ResponseDTO.<Boolean>builder().status(200).success(true).data(result).build();
            return ResponseEntity.ok().body(responseDTO);
        }catch (Exception e){
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .status(400).success(false).Message(e.getMessage())
                    .build();
            return ResponseEntity.ok().body(responseDTO);
        }
    }

    @GetMapping("/checkId")
    public ResponseEntity<ResponseDTO>  checkId(@RequestParam(required = true) String id){
            if (userService.checkId(id)){
                List<Boolean> result=new ArrayList<>();
                result.add(true);
                ResponseDTO responseDTO=ResponseDTO.<Boolean>builder().status(200).success(true).Message("사용 가능한 아이디 입니다.").data(result).build();
                return ResponseEntity.ok().body(responseDTO);
            }
            else{
                List<Boolean> result=new ArrayList<>();
                result.add(false);
                ResponseDTO responseDTO=ResponseDTO.<Boolean>builder().status(200).success(true).Message("중복된 닉네임 입니다.").data(result).build();
                return ResponseEntity.ok().body(responseDTO);
            }

    }
    @GetMapping("/checkNickname")
    public ResponseEntity<ResponseDTO>  checkNickname(@RequestParam(required = true) String nickname){
        if (userService.checkNickname(nickname)){
            List<Boolean> result=new ArrayList<>();
            result.add(true);
            ResponseDTO responseDTO=ResponseDTO.<Boolean>builder().status(200).success(true).Message("사용 가능한 닉네임 입니다.").data(result).build();
            return ResponseEntity.ok().body(responseDTO);
        }
        else{
            List<Boolean> result=new ArrayList<>();
            result.add(false);
            ResponseDTO responseDTO=ResponseDTO.<Boolean>builder().status(200).success(true).Message("중복된 닉네임 입니다.").data(result).build();
            return ResponseEntity.ok().body(responseDTO);
        }

    }


}
