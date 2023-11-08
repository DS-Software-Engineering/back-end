package com.example.dssw.service;

import com.example.dssw.dto.MapDTO;
import com.example.dssw.dto.ReportDeclarationDTO;
import com.example.dssw.dto.RewardHistoryDTO;
import com.example.dssw.model.ReportDeclarationEntity;
import com.example.dssw.model.RewardHistoryEntity;
import com.example.dssw.persistence.ReportDeclarationRepository;
import com.example.dssw.persistence.RewardHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MyPageService {

    @Autowired
    ReportDeclarationRepository reportDeclarationRepository;

    @Autowired
    RewardHistoryRepository rewardHistoryRepository;

    // 내 신고내역 찾기
    public List<ReportDeclarationDTO> getMyDeclaration(Long userid,String type){
        List<ReportDeclarationEntity> entities = reportDeclarationRepository.findByUser_IdAndType(userid,type);
        entities = entities.stream().map(entity -> { entity.setContext(null); return entity;}).collect(Collectors.toList());
        List<ReportDeclarationDTO> dtos = entities.stream().map(ReportDeclarationDTO::new).collect(Collectors.toList());
        return dtos;
    }


    public List<RewardHistoryDTO> getMyRewardHistory(Long userid) {
        return rewardHistoryRepository.findByUserId(userid);
    }

    public ReportDeclarationDTO getDeclarationDetail(Long id) throws Exception {
        Optional<ReportDeclarationEntity> entity = reportDeclarationRepository.findById(id);
        ReportDeclarationEntity declaration = entity.orElseThrow(() -> new Exception("엔터티가 존재하지 않습니다."));
        ReportDeclarationDTO dto= new ReportDeclarationDTO(declaration);
        return dto;
    }

    // 리워드와 닉네임

}
