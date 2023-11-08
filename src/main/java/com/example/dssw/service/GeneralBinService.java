package com.example.dssw.service;

import com.example.dssw.dto.GeneralBinDTO;
import com.example.dssw.dto.MapDTO;
import com.example.dssw.model.GeneralBinEntity;
import com.example.dssw.persistence.GeneralBinRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class GeneralBinService {
    @Autowired
    GeneralBinRepository repository;

    public List<MapDTO> getAll(){
        return repository.find_all_Latitude_Longtitude();
    }

    public List<MapDTO> getBinByType(String type){

        if(type.equals("general")){return repository.find_general_Latitude_Longtitude();}
        else if(type.equals("cb")){return repository.find_cb_Latitude_Longtitude();}
        else if(type.equals("drink")){return repository.find_drink_Latitude_Longtitude();}
        else if(type.equals("recycle")){return repository.find_recycle_Latitude_Longtitude();}
        else return null;


    }

    public GeneralBinDTO getDetailGeneralInfo(Long binId){
        GeneralBinEntity bin = repository.findById(binId).orElseThrow(() -> new IllegalArgumentException("해당하는 쓰레기통이 없습니다."));

        GeneralBinDTO res = new GeneralBinDTO();

        res.setStreet_name(bin.getStreet_name());
        res.setAddress(bin.getAddress());
        res.setDetail_location(bin.getDetail_location());
        res.setType_general(bin.isType_general());
        res.setType_cb(bin.isType_cb());
        res.setType_recycle(bin.isType_recycle());
        res.setType_drink(bin.isType_drink());
        res.setShape(bin.getShape());


        return res;
    }
}
