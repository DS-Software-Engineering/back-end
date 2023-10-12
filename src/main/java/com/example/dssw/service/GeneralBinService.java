package com.example.dssw.service;

import com.example.dssw.dto.MapDTO;
import com.example.dssw.model.GeneralBinEntity;
import com.example.dssw.model.RecycleBinEntity;
import com.example.dssw.persistence.GeneralBinRepository;
import com.example.dssw.persistence.RecycleBinRepository;
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
}
