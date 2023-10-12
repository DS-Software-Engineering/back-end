package com.example.dssw.persistence;

import com.example.dssw.dto.MapDTO;
import com.example.dssw.model.GeneralBinEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GeneralBinRepository extends JpaRepository<GeneralBinEntity,Long> {
    @Query("SELECT new com.example.dssw.dto.MapDTO(g.id, g.latitude, g.longtitude , 'GeneralBin') FROM GeneralBinEntity g")
    List<MapDTO> find_all_Latitude_Longtitude();

    @Query(value="SELECT new com.example.dssw.dto.MapDTO(g.id, g.latitude, g.longtitude ,'GeneralBin') FROM GeneralBinEntity g WHERE type_cb = true ")
    List<MapDTO> find_cb_Latitude_Longtitude();

    @Query("SELECT new com.example.dssw.dto.MapDTO(g.id, g.latitude, g.longtitude , 'GeneralBin') FROM GeneralBinEntity g WHERE type_drink = true ")
    List<MapDTO> find_drink_Latitude_Longtitude();

    @Query("SELECT new com.example.dssw.dto.MapDTO(g.id, g.latitude, g.longtitude , 'GeneralBin') FROM GeneralBinEntity g WHERE g.type_general = true")
    List<MapDTO> find_general_Latitude_Longtitude();

    @Query("SELECT new com.example.dssw.dto.MapDTO(g.id, g.latitude, g.longtitude , 'GeneralBin') FROM GeneralBinEntity g WHERE g.type_recycle = true")
    List<MapDTO> find_recycle_Latitude_Longtitude();

}
