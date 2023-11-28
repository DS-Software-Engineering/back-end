package com.example.dssw.user;

import com.example.dssw.model.UserEntity;
import com.example.dssw.persistence.UserRepository;
import com.example.dssw.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    // 회원가입
    @Test
    @Transactional
    void userSignUpTest() throws Exception {
        // given
        // 유저 데이터 생성
        UserEntity user = new UserEntity();
        user.setAdmin(false);
        user.setNickname("깔끔시민");
        user.setUserid("test");
        user.setPassword("1234");
        user.setPhonenum("01012345678");
        user.setReward(0);

        // when
        userService.create(user);

        // then
        Optional<UserEntity> findUser = userRepository.findById(user.getId());
        assertThat(user.getUserid()).isEqualTo(findUser.get().getUserid());
    }

}
