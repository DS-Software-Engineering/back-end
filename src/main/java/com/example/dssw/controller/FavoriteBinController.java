package com.example.dssw.controller;

import com.example.dssw.dto.LikeDTO;
import com.example.dssw.dto.ResponseDTO;
import com.example.dssw.dto.tokenDTO;
import com.example.dssw.service.FavoriteBinService;
import com.example.dssw.service.GeneralBinService;
import com.example.dssw.service.RecycleBinService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/favorite")
public class FavoriteBinController {
    @Autowired
    GeneralBinService generalBinService;
    @Autowired
    RecycleBinService recycleBinService;

    private final FavoriteBinService favoriteBinService;

    @PostMapping("/create")
    public ResponseEntity<?> createFavorite(@AuthenticationPrincipal String userId, @RequestBody LikeDTO likeDTO){
        favoriteBinService.createLike(Long.parseLong(userId), likeDTO);

        ResponseDTO responseDTO=ResponseDTO.<tokenDTO>builder().status(200).success(true).Message("좋아요 생성 성공").build();

        return ResponseEntity.ok().body(responseDTO);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteLike(@AuthenticationPrincipal String userId, @RequestBody LikeDTO likeDTO){
        favoriteBinService.deleteLike(Long.parseLong(userId), likeDTO);

        ResponseDTO responseDTO=ResponseDTO.<tokenDTO>builder().status(200).success(true).Message("좋아요 삭제 성공").build();

        return ResponseEntity.ok().body(responseDTO);
    }

}
