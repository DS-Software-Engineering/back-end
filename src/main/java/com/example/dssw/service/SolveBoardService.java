package com.example.dssw.service;

import com.example.dssw.dto.SolveBoardDTO;
import com.example.dssw.model.SolveBoardEntity;
import com.example.dssw.model.SolveBoardImagesEntity;
import com.example.dssw.model.UserEntity;
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
    AmazonS3Service amazonS3Service;

    @Autowired
    UserRepository userRepository;

    public List<SolveBoardEntity> getAllBoards() {
        return solveBoardRepository.findAll();
    }

    public SolveBoardEntity getBoardById(Long id) {
        return solveBoardRepository.findById(id).orElse(null);
    }

    public SolveBoardEntity createBoard(String userId, SolveBoardDTO board) {

        Optional<UserEntity> user = userRepository.findById(Long.parseLong(userId));

        SolveBoardEntity solveBoardEntity = new SolveBoardEntity();
        solveBoardEntity.setTitle(board.getTitle());
        solveBoardEntity.setContext(board.getContext());
        solveBoardEntity.setDate(LocalDateTime.now());
        solveBoardEntity.setUser(user.get());

        solveBoardRepository.save(solveBoardEntity);

        return solveBoardEntity;

    }

    public SolveBoardEntity createBoardWithImages(String userId, SolveBoardDTO board, List<String> imageUrls) {

        Optional<UserEntity> user = userRepository.findById(Long.parseLong(userId));

        SolveBoardEntity solveBoardEntity = new SolveBoardEntity();
        solveBoardEntity.setTitle(board.getTitle());
        solveBoardEntity.setContext(board.getContext());
        solveBoardEntity.setDate(LocalDateTime.now());
        solveBoardEntity.setUser(user.get());

        for (String imageUrl : imageUrls) {
            SolveBoardImagesEntity imageEntity = new SolveBoardImagesEntity();
            imageEntity.setImage_url(imageUrl);
            imageEntity.setSolveBoard(solveBoardEntity);
            solveBoardEntity.getImages().add(imageEntity);
        }

        solveBoardRepository.save(solveBoardEntity);

        return solveBoardEntity;

    }

    public void deleteBoard(Long id) {
        solveBoardRepository.deleteById(id);
    }

}
