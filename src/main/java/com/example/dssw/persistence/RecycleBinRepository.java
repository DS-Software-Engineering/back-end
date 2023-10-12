package com.example.dssw.persistence;

import com.example.dssw.dto.MapDTO;
import com.example.dssw.model.RecycleBinEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RecycleBinRepository extends JpaRepository<RecycleBinEntity,Long> {
    @Query("SELECT new com.example.dssw.dto.MapDTO(r.id, r.latitude, r.longtitude , 'RecycleBin') FROM RecycleBinEntity r")
    List<MapDTO> find_all_Latitude_Longtitude();
}
