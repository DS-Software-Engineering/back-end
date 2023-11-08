package com.example.dssw.controller;

import com.example.dssw.dto.ReportDeclarationDTO;
import com.example.dssw.dto.ResponseDTO;
import com.example.dssw.dto.RewardHistoryDTO;
import com.example.dssw.service.MyPageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/myPage")
public class MyPageController {
    @Autowired
    MyPageService myPageService;
    @GetMapping("/declaration/{type}")
    public ResponseEntity<?> GetMyDeclaration(@AuthenticationPrincipal String userId, @PathVariable(name = "type") String type){
        List<ReportDeclarationDTO> result = myPageService.getMyDeclaration(Long.parseLong(userId),type);
        ResponseDTO responseDTO= ResponseDTO.builder().status(200).success(true).data(Collections.singletonList(result)).build();
        return ResponseEntity.ok().body(responseDTO);
    }

    // 신고내역 상세 정보
    @GetMapping("/declaration/detail/{id}")
    public ResponseEntity<?> GetDeclarationDetail(@AuthenticationPrincipal String userId,@PathVariable(name = "id") Long id){
        try{
            ReportDeclarationDTO result = myPageService.getDeclarationDetail(id);
            ResponseDTO responseDTO= ResponseDTO.builder().status(200).success(true).data(Collections.singletonList(result)).build();
            return ResponseEntity.ok().body(responseDTO);
        }catch(Exception e){
            ResponseDTO responseDTO= ResponseDTO.builder().status(400).success(false).Message(e.getMessage()).build();
            return ResponseEntity.ok().body(responseDTO);
        }

    }

    // 리워드 히스토리
    @GetMapping("/reward")
    public ResponseEntity<?> getMyRewardHistory(@AuthenticationPrincipal String userId){
        List<RewardHistoryDTO> result = myPageService.getMyRewardHistory(Long.parseLong(userId));
        ResponseDTO responseDTO= ResponseDTO.builder().status(200).success(true).data(Collections.singletonList(result)).build();
        return ResponseEntity.ok().body(responseDTO);
    }







}
