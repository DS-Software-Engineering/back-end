package com.example.dssw.controller;

import com.example.dssw.dto.DeclarationDTO;
import com.example.dssw.dto.ResponseDTO;
import com.example.dssw.dto.SolveBoardDTO;
import com.example.dssw.dto.SolveBoardResponseDTO;
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

import java.util.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/solveBoard")
public class SolveBoardController {

    @Autowired
    SolveBoardService solveBoardService;

    @Autowired
    AmazonS3Service amazonS3Service;

    @PostMapping("/post")
    public ResponseEntity<?> createBoardWithImages(@AuthenticationPrincipal String userId, @ModelAttribute SolveBoardDTO boardDTO, @RequestPart(value = "images", required = false) List<MultipartFile> images) {
        ResponseDTO<Object> responseDTO = new ResponseDTO<>();

        try {
            // s3 upload
            String folderName = "solveBoard";
            List<String> imageUrls = amazonS3Service.uploadFiles(folderName, images);

            // save board with image URLs
            SolveBoardEntity createBoard = solveBoardService.createBoardWithImages(userId, boardDTO, imageUrls);

            Long solveBoardId = createBoard.getId();
            responseDTO = ResponseDTO.builder().status(200).success(true).Message("주요 처리 사례 게시글 작성 성공").data(Collections.singletonList(solveBoardId)).build();

            return ResponseEntity.ok().body(responseDTO);
        } catch (Exception e) {
            responseDTO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseDTO.setSuccess(false);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setData((Collections.emptyList()));

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
        }

    }

    @GetMapping("/list")
    public ResponseEntity<?> getSolveBoardList() {
        ResponseDTO<Object> responseDTO = null;
        List<Object> result = new ArrayList<>();

        try {
            List<SolveBoardResponseDTO> solveBoards = solveBoardService.getAllSolveBoards();
            result.addAll(solveBoards);

            responseDTO = ResponseDTO.builder().status(200).success(true).Message("주요처리사례 리스트").data(result).build();

            return ResponseEntity.ok().body(responseDTO);
        } catch (Exception e) {
            responseDTO = ResponseDTO.builder().status(HttpStatus.INTERNAL_SERVER_ERROR.value()).success(false).Message(e.getMessage()).data(Collections.emptyList()).build();

            return ResponseEntity.ok().body(responseDTO);
        }

    }

    @GetMapping("/list/{id}")
    public ResponseEntity<?> getSolveBoardById(@PathVariable Long id) {
        ResponseDTO<Object> responseDTO = null;
        List<Object> result = new ArrayList<>();

        SolveBoardResponseDTO solveBoard = solveBoardService.getSolveBoardDetail(id);
        result.add(solveBoard);

        responseDTO = ResponseDTO.builder().status(200).success(true).data(result).build();

        return ResponseEntity.ok().body(responseDTO);
    }

}
