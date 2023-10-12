package com.example.dssw.service;

import com.example.dssw.dto.MapDTO;
import com.example.dssw.model.RecycleBinEntity;
import com.example.dssw.persistence.RecycleBinRepository;
import io.jsonwebtoken.impl.crypto.MacProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class RecycleBinService {
    @Autowired
    RecycleBinRepository repository;

    public List<MapDTO> getAll(){
        return repository.find_all_Latitude_Longtitude();
    }


}
