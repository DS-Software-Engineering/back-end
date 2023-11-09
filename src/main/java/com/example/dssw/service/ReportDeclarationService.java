package com.example.dssw.service;

import com.example.dssw.dto.DeclarationDTO;
import com.example.dssw.model.ReportDeclarationEntity;
import com.example.dssw.model.UserEntity;
import com.example.dssw.persistence.ReportDeclarationRepository;
import com.example.dssw.persistence.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ReportDeclarationService {
    @Autowired
    ReportDeclarationRepository reportDeclarationRepository;

    @Autowired
    AmazonS3Service amazonS3Service;

    @Autowired
    UserRepository userRepository;


    public Long uploadPost(Long userId, MultipartFile multipartFile, DeclarationDTO.uploadDeclarationDTO declarationDTO){
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당하는 유저가 없습니다."));

        List<MultipartFile> files = new ArrayList<>();
        files.add(multipartFile);

        List<String> result = amazonS3Service.uploadFiles("declaration", files);

        ReportDeclarationEntity post = ReportDeclarationEntity.builder()
                .user(user)
                .address(declarationDTO.getAddress())
                .detail_location(declarationDTO.getDetail_location())
                .context(declarationDTO.getContext())
                .type(declarationDTO.getType())
                .image_url(result.get(0))
                .build();

        ReportDeclarationEntity uploadPost = reportDeclarationRepository.save(post);
        if (uploadPost == null) return null;

        return uploadPost.getId();
    }

    public DeclarationDTO.getDeclarationDTO getPost(Long postId){

        ReportDeclarationEntity post = reportDeclarationRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("해당하는 유저가 없습니다."));

        DeclarationDTO.getDeclarationDTO result = DeclarationDTO.getDeclarationDTO.builder()
                .user(post.getUser().getId())
                .address(post.getAddress())
                .detail_location(post.getDetail_location())
                .context(post.getContext())
                .type(post.getType())
                .date(post.getDate())
                .image_url(post.getImage_url())
                .build();

        return result;
    }
}
