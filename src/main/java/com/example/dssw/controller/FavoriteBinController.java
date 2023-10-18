package com.example.dssw.controller;

import com.example.dssw.dto.CreateLikeDTO;
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
    public ResponseEntity<?> createFavorite(@RequestBody CreateLikeDTO createLikeDTO){
        favoriteBinService.createLike(createLikeDTO);

        return ResponseEntity.ok().body("success");
    }

    @GetMapping("/check")
    public ResponseEntity<?> checkLike(@RequestParam String userId, @RequestParam(value="binId") Long binId, @RequestParam(value="binType") String binType){
        boolean checkFav = favoriteBinService.checkFavBin(userId, binId, binType);

        return ResponseEntity.ok().body(checkFav);
    }
}
