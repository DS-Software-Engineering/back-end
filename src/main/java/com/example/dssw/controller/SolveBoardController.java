package com.example.dssw.controller;

import com.example.dssw.dto.DeclarationDTO;
import com.example.dssw.dto.ResponseDTO;
import com.example.dssw.dto.SolveBoardDTO;
import com.example.dssw.model.SolveBoardEntity;
import com.example.dssw.service.AmazonS3Service;
import com.example.dssw.service.SolveBoardService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/solveBoard")
public class SolveBoardController {

    @Autowired
    SolveBoardService solveBoardService;

    @Autowired
    AmazonS3Service amazonS3Service;

    @GetMapping
    public List<SolveBoardEntity> getAllSolveBoards() {
        return solveBoardService.getAllBoards();
    }

    @PostMapping("/post")
    public ResponseEntity<?> createBoardWithImages(@AuthenticationPrincipal String userId, @ModelAttribute SolveBoardDTO boardDTO, @RequestPart(value = "images", required = false) List<MultipartFile> images) {
        ResponseDTO<Object> responseDTO = new ResponseDTO<>();

        try {
            // s3 upload
            String folderName = "solveBoard";
            List<String> imageUrls = amazonS3Service.uploadFiles(folderName, images);

            // save board with image URLs
            SolveBoardEntity createBoard = solveBoardService.createBoardWithImages(userId, boardDTO, imageUrls);

            responseDTO = ResponseDTO.builder().status(200).success(true).Message("주요 처리 사례 게시글 작성 성공").data(Collections.singletonList(createBoard)).build();

            return ResponseEntity.ok().body(responseDTO);
        } catch (Exception e) {
            responseDTO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseDTO.setSuccess(false);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setData((Collections.emptyList()));

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
        }

    }

    @PostMapping("/upload")
    public ResponseEntity<?> createBoard(@AuthenticationPrincipal String userId, @RequestBody SolveBoardDTO boardDTO) {
        ResponseDTO<Object> responseDTO = new ResponseDTO<>();

        try {
            SolveBoardEntity createBoard = solveBoardService.createBoard(userId, boardDTO);
            responseDTO = ResponseDTO.builder().status(200).success(true).Message("주요 처리 사례 게시글 작성 성공").data(Collections.singletonList(createBoard)).build();

            return ResponseEntity.ok().body(responseDTO);
        } catch (Exception e) {
            responseDTO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseDTO.setSuccess(false);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setData((Collections.emptyList()));

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBoard(@PathVariable Long id) {
        solveBoardService.deleteBoard(id);
        return ResponseEntity.ok().build();
    }

//    @PostMapping("/uploadPost")
//    public ResponseEntity<?> upload(@AuthenticationPrincipal String userId, @RequestParam("images") MultipartFile multipartFile, @ModelAttribute SolveBoardDTO solveBoardDTO) throws JsonProcessingException {
//        ResponseDTO responseDTO=null;
//
//        ObjectMapper mapper = new ObjectMapper();
//        SolveBoardDTO mapperUploadPostDTO = mapper.readValue(solveBoardDTO, SolveBoardDTO.class);
//
//        List<Long> result = new ArrayList<>();
//        Long postId = solveBoardService.uploadPost(Long.parseLong(userId), multipartFile, mapperUploadPostDTO);
//        result.add(postId);
//
//        responseDTO= ResponseDTO.<Long>builder().status(200).success(true).data(result).build();
//
//        return ResponseEntity.ok().body(responseDTO);
//    }
}
