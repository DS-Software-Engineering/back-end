package com.example.dssw.service;

import com.example.dssw.dto.SolveBoardDTO;
import com.example.dssw.dto.SolveBoardResponseDTO;
import com.example.dssw.model.SolveBoardEntity;
import com.example.dssw.model.SolveBoardImagesEntity;
import com.example.dssw.model.UserEntity;
import com.example.dssw.persistence.SolveBoardImagesRepository;
import com.example.dssw.persistence.SolveBoardRepository;
import com.example.dssw.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SolveBoardService {

    @Autowired
    SolveBoardRepository solveBoardRepository;

    @Autowired
    SolveBoardImagesRepository solveBoardImagesRepository;

    @Autowired
    UserRepository userRepository;

    public Optional<SolveBoardEntity> getBoardById(Long id) {
        return solveBoardRepository.findById(id);
    }

    public SolveBoardEntity createBoardWithImages(String userId, SolveBoardDTO board, List<String> imageUrls) {

        Optional<UserEntity> user = userRepository.findById(Long.parseLong(userId));

        SolveBoardEntity solveBoardEntity = new SolveBoardEntity();
        solveBoardEntity.setTitle(board.getTitle());
        solveBoardEntity.setContext(board.getContext());
        solveBoardEntity.setDate(LocalDateTime.now());
        solveBoardEntity.setUser(user.get());

        // 게시글 db에 저장
        solveBoardRepository.save(solveBoardEntity);

        // 이미지 db에 저장
        for (String imageUrl : imageUrls) {
            SolveBoardImagesEntity imageEntity = new SolveBoardImagesEntity();
            imageEntity.setImage_url(imageUrl);
            imageEntity.setSolveBoard(solveBoardEntity);

            solveBoardImagesRepository.save(imageEntity);
        }

        return solveBoardEntity;
    }

    public List<SolveBoardResponseDTO> getAllSolveBoards() {
        List<SolveBoardEntity> solveBoards = solveBoardRepository.findAll();

        List<SolveBoardResponseDTO> solveBoardsWithImages = new ArrayList<>();

        for (SolveBoardEntity solveBoard : solveBoards) {
            // 해당 게시글의 이미지들 가져옴
            List<SolveBoardImagesEntity> image = solveBoardImagesRepository.findBySolveBoard(solveBoard);

            List<String> images = new ArrayList<>();
            for (int i=0; i<image.size(); i++) {
                images.add(image.get(i).getImage_url());
            }

            SolveBoardResponseDTO solveBoardResponseDTO = SolveBoardResponseDTO.builder()
                    .id(solveBoard.getId())
                    .title(solveBoard.getTitle())
                    .context(solveBoard.getContext())
                    .userNickname(solveBoard.getUser().getNickname())
                    .date(solveBoard.getDate())
                    .image_url(images)
                    .build();

            solveBoardsWithImages.add(solveBoardResponseDTO);
        }
        return solveBoardsWithImages;
    }

    public SolveBoardResponseDTO getSolveBoardDetail(Long id) {
        Optional<SolveBoardEntity> solveBoard = solveBoardRepository.findById(id);

        List<SolveBoardImagesEntity> image = solveBoardImagesRepository.findBySolveBoard(solveBoard.get());

        List<String> images = new ArrayList<>();
        for (int i=0; i<image.size(); i++) {
            images.add(image.get(i).getImage_url());
        }

        SolveBoardResponseDTO solveBoardResponseDTO = SolveBoardResponseDTO.builder()
                .id(solveBoard.get().getId())
                .title(solveBoard.get().getTitle())
                .context(solveBoard.get().getContext())
                .userNickname(solveBoard.get().getUser().getNickname())
                .date(solveBoard.get().getDate())
                .image_url(images)
                .build();

        return solveBoardResponseDTO;
    }

}
