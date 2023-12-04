package com.example.dssw.service;

import com.example.dssw.base.event.FavoriteCreatedEvent;
import com.example.dssw.dto.LikeDTO;
import com.example.dssw.model.FavoriteBinEntity;
import com.example.dssw.model.GeneralBinEntity;
import com.example.dssw.model.RecycleBinEntity;
import com.example.dssw.model.UserEntity;
import com.example.dssw.persistence.FavoriteBinRepository;
import com.example.dssw.persistence.GeneralBinRepository;
import com.example.dssw.persistence.RecycleBinRepository;
import com.example.dssw.persistence.UserRepository;
import jakarta.transaction.Transactional;
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


    public void createLike(Long userId, LikeDTO createLikeDTO){
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당하는 유저가 없습니다."));

        if(createLikeDTO.getBinType().equals("general")){
            generalBinRepository.findById(createLikeDTO.getBinId()).orElseThrow(() -> new IllegalArgumentException("해당하는 쓰레기통이 없습니다."));

        } else if (createLikeDTO.getBinType().equals("recycle")) {
            recycleBinRepository.findById(createLikeDTO.getBinId()).orElseThrow(() -> new IllegalArgumentException("해당하는 쓰레기통이 없습니다."));

        } else {
            throw new IllegalArgumentException("해당하는 쓰레기통이 없습니다.");
        }

        Optional<FavoriteBinEntity> fav = favoriteBinRepository.findByUserAndBinIdAndBinType(user, createLikeDTO.getBinId(), createLikeDTO.getBinType());

        if(fav.isPresent()){
            throw new IllegalArgumentException("좋아요가 이미 눌려져있습니다.");
        }

        FavoriteBinEntity favorite = FavoriteBinEntity.builder()
                .user(user)
                .binId(createLikeDTO.getBinId())
                .binType(createLikeDTO.getBinType())
                .build();


        favoriteBinRepository.save(favorite);

        FavoriteCreatedEvent event = new FavoriteCreatedEvent(this, favorite);
        eventPublisher.publishEvent(event);
    }


    public Boolean checkFavBin(Long userId, Long binId, String binType){
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당하는 유저가 없습니다."));

        Optional<FavoriteBinEntity> fav = favoriteBinRepository.findByUserAndBinIdAndBinType(user, binId, binType);

        if(!fav.isPresent()){
            return false;
        }else {
            return true;
        }

    }

    @Transactional
    public void deleteLike(Long userId, LikeDTO likeDTO){
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당하는 유저가 없습니다."));

        FavoriteBinEntity fav = favoriteBinRepository.findByUserAndBinIdAndBinType(user, likeDTO.getBinId(), likeDTO.getBinType()).orElseThrow(() -> new IllegalArgumentException("해당하는 좋아요가 없습니다."));

        favoriteBinRepository.deleteById(fav.getId());
    }


}
