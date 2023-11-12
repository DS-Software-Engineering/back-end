package com.example.dssw.controller;

import com.example.dssw.dto.DeclarationDTO;
import com.example.dssw.dto.ReportDeclaration.UploadDeclarationDTO;
import com.example.dssw.dto.ResponseDTO;
import com.example.dssw.service.ReportDeclarationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/declaration")
public class ReportDeclarationController {

    @Autowired
    ReportDeclarationService reportDeclarationService;

    @PostMapping("/uploadPost")
    public ResponseEntity<?> upload(@AuthenticationPrincipal String userId, @RequestParam("images") MultipartFile multipartFile, @RequestParam(value="info")String uploaDeclarationDTO) throws JsonProcessingException {
        ResponseDTO responseDTO=null;

        ObjectMapper mapper = new ObjectMapper();
        UploadDeclarationDTO mapperUploadPostDTO = mapper.readValue(uploaDeclarationDTO, UploadDeclarationDTO.class);


        List<Long> res = new ArrayList<>();
        Long postId = reportDeclarationService.uploadPost(Long.parseLong(userId), multipartFile, mapperUploadPostDTO);
        res.add(postId);

        responseDTO= ResponseDTO.<Long>builder().status(200).success(true).data(res).build();

        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/getPost")
    public ResponseEntity<?> getPost(@RequestParam Long postId){
        ResponseDTO responseDTO=null;

        List<DeclarationDTO.getDeclarationDTO> res = new ArrayList<>();
        DeclarationDTO.getDeclarationDTO post = reportDeclarationService.getPost(postId);
        res.add(post);

        responseDTO= ResponseDTO.<DeclarationDTO.getDeclarationDTO>builder().status(200).success(true).data(res).build();

        return ResponseEntity.ok().body(responseDTO);
    }
}
