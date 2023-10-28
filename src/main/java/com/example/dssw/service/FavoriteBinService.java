package com.example.dssw.service;

import com.example.dssw.base.event.FavoriteCreatedEvent;
import com.example.dssw.dto.CreateLikeDTO;
import com.example.dssw.model.FavoriteBinEntity;
import com.example.dssw.model.GeneralBinEntity;
import com.example.dssw.model.RecycleBinEntity;
import com.example.dssw.model.UserEntity;
import com.example.dssw.persistence.FavoriteBinRepository;
import com.example.dssw.persistence.GeneralBinRepository;
import com.example.dssw.persistence.RecycleBinRepository;
import com.example.dssw.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class FavoriteBinService {
    private final GeneralBinRepository generalBinRepository;
    private final RecycleBinRepository recycleBinRepository;

    private final UserRepository userRepository;
    private final FavoriteBinRepository favoriteBinRepository;
    private final ApplicationEventPublisher eventPublisher;
    public Long createLike(CreateLikeDTO createLikeDTO){
        UserEntity user = userRepository.findByUserid(createLikeDTO.getUserId()).orElseThrow(() -> new IllegalArgumentException("해당하는 유저가 없습니다."));

        Long binId = null;

        if(createLikeDTO.getBinType().equals("general")){
            GeneralBinEntity bin = generalBinRepository.findById(createLikeDTO.getBinId()).orElseThrow(() -> new IllegalArgumentException("해당하는 쓰레기통이 없습니다."));
            binId = bin.getId();

        } else if (createLikeDTO.getBinType().equals("recycle")) {
            RecycleBinEntity bin = recycleBinRepository.findById(createLikeDTO.getBinId()).orElseThrow(() -> new IllegalArgumentException("해당하는 쓰레기통이 없습니다."));
            binId = bin.getId();

        } else {
            new IllegalArgumentException("해당하는 쓰레기통이 없습니다.");
        }

        FavoriteBinEntity fav = FavoriteBinEntity.builder()
                .user(user)
                .binId(binId)
                .binType(createLikeDTO.getBinType())
                .build();


        favoriteBinRepository.save(fav);

        FavoriteCreatedEvent event = new FavoriteCreatedEvent(this, fav);
        eventPublisher.publishEvent(event);

        return favoriteBinRepository.save(fav).getId();
    }


    public Boolean checkFavBin(String userId, Long binId, String binType){
        UserEntity user = userRepository.findByUserid(userId).orElseThrow(() -> new IllegalArgumentException("해당하는 유저가 없습니다."));

        if(binType.equals("general")){
            GeneralBinEntity bin = generalBinRepository.findById(binId).orElseThrow(() -> new IllegalArgumentException("해당하는 쓰레기통이 없습니다."));
        } else if (binType.equals("recycle")) {
            RecycleBinEntity bin = recycleBinRepository.findById(binId).orElseThrow(() -> new IllegalArgumentException("해당하는 쓰레기통이 없습니다."));
        } else {
            new IllegalArgumentException("해당하는 쓰레기통이 없습니다.");
        }

        Optional<FavoriteBinEntity> fav = favoriteBinRepository.findByUserAndBinIdAndBinType(user, binId, binType);

        if(!fav.isPresent()){
            return false;
        }else {
            return true;
        }
    }

}