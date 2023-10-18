package com.example.dssw.service;

import com.example.dssw.dto.MapDTO;
import com.example.dssw.dto.RecycleBinDTO;
import com.example.dssw.model.RecycleBinEntity;
import com.example.dssw.persistence.RecycleBinRepository;
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

    public RecycleBinDTO getDetailRecycleInfo(Long binId){
        RecycleBinEntity bin = repository.findById(binId).orElseThrow(() -> new IllegalArgumentException("해당하는 쓰레기통이 없습니다."));

        RecycleBinDTO res = new RecycleBinDTO();

        res.setVillage_name(bin.getVillage_name());
        res.setAddress(bin.getAddress());
        res.setDetail_location(bin.getDetail_location());
        res.setCenter_phone_num(bin.getCenter_phone_num());

        return res;
    }

}
