package com.example.dssw.persistence;

import com.example.dssw.dto.FavoriteBinDTO;
import com.example.dssw.dto.MapDTO;
import com.example.dssw.model.RecycleBinEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RecycleBinRepository extends JpaRepository<RecycleBinEntity,Long> {
    @Query("SELECT new com.example.dssw.dto.MapDTO(r.id, r.latitude, r.longtitude , 'RecycleBin') FROM RecycleBinEntity r")
    List<MapDTO> find_all_Latitude_Longtitude();

    @Query("SELECT rb FROM RecycleBinEntity rb WHERE rb.village_name LIKE %:keyword% OR rb.address LIKE %:keyword% OR rb.detail_location LIKE %:keyword%")
    List<RecycleBinEntity> searchRecycleBins(@Param("keyword") String keyword);

    @Query("SELECT new com.example.dssw.dto.FavoriteBinDTO(b.id,b.latitude,b.longtitude,'RecycleBin',b.address,b.detail_location)  " +
            "FROM RecycleBinEntity b where b.id in (select f.binId from FavoriteBinEntity f where f.user.id = :userid and binType='recycle')")
    List<FavoriteBinDTO> searchFavoriteRecycleBin(@Param("userid") Long userid);
}
