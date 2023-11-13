package com.example.dssw.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class SolveBoardImagesDTO {

    private List<MultipartFile> files;
}
