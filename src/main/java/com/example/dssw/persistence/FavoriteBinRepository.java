package com.example.dssw.persistence;

import com.example.dssw.model.FavoriteBinEntity;
import com.example.dssw.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FavoriteBinRepository extends JpaRepository<FavoriteBinEntity,Long> {
//    Optional<FavoriteBinEntity> findByUser_IdAndBinIdAndBinType(UserEntity userid, Long binId, String binType);

    Optional<FavoriteBinEntity> findByUserAndBinIdAndBinType(UserEntity user, Long binId, String binType);
}
