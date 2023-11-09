package com.example.dssw.persistence;

import com.example.dssw.dto.FavoriteBinDTO;
import com.example.dssw.dto.MapDTO;
import com.example.dssw.model.GeneralBinEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    // 검색 및 필터링 메서드
    @Query("SELECT b FROM GeneralBinEntity b WHERE b.street_name LIKE %:keyword% OR b.address LIKE %:keyword% OR b.detail_location LIKE %:keyword%")
    List<GeneralBinEntity> searchGeneralBins(@Param("keyword") String keyword);


    Optional<GeneralBinEntity> findById(Long Id);

    @Query("SELECT new com.example.dssw.dto.FavoriteBinDTO(b.id ,b.latitude,b.longtitude,'GeneralBin',b.address,b.detail_location)  " +
            "FROM GeneralBinEntity b where b.id in (select f.binId from FavoriteBinEntity f where f.user.id = :userid and binType='GeneralBin')")
    List<FavoriteBinDTO> searchFavoriteGeneralBin(@Param("userid") Long userid);

}
