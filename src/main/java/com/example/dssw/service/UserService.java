package com.example.dssw.service;

import com.example.dssw.model.UserEntity;
import com.example.dssw.persistence.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void create(final UserEntity userEntity) throws Exception {



        if(userRepository.existsByNickname(userEntity.getNickname())){ //중복 불가!
            throw new RuntimeException("이미 존재하는 닉네임입니다.");
        }
        else if(userRepository.existsByUserid(userEntity.getUserid())){ //중복 불가!
            throw new RuntimeException("이미 존재하는 아이디입니다.");
        }

        userRepository.save(userEntity);

    }

    public UserEntity getByCredentials(final String userid, final String password , final PasswordEncoder encoder){
        // 인증정보로 userEntity 검색.
        final Optional<UserEntity> originalUser=userRepository.findByUserid(userid);
        if(originalUser.isPresent() && encoder.matches(password,originalUser.get().getPassword())){
            return originalUser.get();
        }
        return null;

        // return userRepository.findByEmailAndPassword(email,password);
    }


}