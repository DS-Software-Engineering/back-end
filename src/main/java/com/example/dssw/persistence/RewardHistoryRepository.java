package com.example.dssw.persistence;

import com.example.dssw.dto.RewardHistoryDTO;
import com.example.dssw.model.RewardHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
//    private Long id;
//
//    private String address;
//
//    private String detail_location;
//
//    private LocalDateTime date; // 업로드 날짜 시간 분 초
//
//    private Long report_id;
//
//    private int reward; // 리워드 포인트
@Repository
public interface RewardHistoryRepository  extends JpaRepository<RewardHistoryEntity,Long> {
    //    @Query(value = "select new com.todis.todisweb.demo.dto.FriendListDetailDto(u.name, u.profileImageUrl, u.codyImage, u.comment) from User u where u.id in (select fl.friendId from FriendList fl where fl.userId = :userId)")
    //    List<FriendListDetailDto> findFriendIdByUserIdDetail(@Param("userId") int user_id);
    @Query(value = "select new com.example.dssw.dto.RewardHistoryDTO(r.id,d.address,d.detail_location,d.date,r.report_id,r.reward) from RewardHistoryEntity r,ReportDeclarationEntity d where r.user.id = :userid and d.id = r.report_id")
    List<RewardHistoryDTO> findByUserId(@Param("userid") Long userid);
}
