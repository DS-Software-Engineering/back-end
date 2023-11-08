package com.example.dssw.service;

import com.example.dssw.dto.MapDTO;
import com.example.dssw.dto.ReportDeclarationDTO;
import com.example.dssw.model.ReportDeclarationEntity;
import com.example.dssw.persistence.ReportDeclarationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MyPageService {

    @Autowired
    ReportDeclarationRepository reportDeclarationRepository;

    // 내 신고내역 찾기
    public List<ReportDeclarationDTO> getMyDeclaration(Long userid,String type){
        System.out.println(userid);
        List<ReportDeclarationEntity> entities = reportDeclarationRepository.findByUser_IdAndType(userid,type);
        entities = entities.stream().map(entity -> { entity.setContext(null); return entity;}).collect(Collectors.toList());
        List<ReportDeclarationDTO> dtos = entities.stream().map(ReportDeclarationDTO::new).collect(Collectors.toList());
        return dtos;
    }


}
