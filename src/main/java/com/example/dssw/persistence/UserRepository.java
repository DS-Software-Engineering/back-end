package com.example.dssw.persistence;

import com.example.dssw.model.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    boolean existsByNickname(String nickname);
    boolean existsByUserid(String userid);

    Optional<UserEntity> findByUserid (String userid);

    Optional<UserEntity> findByPhonenum(String phonenum);

    Optional<UserEntity> findByUseridAndPhonenum (String userid,String phonenum);
}
