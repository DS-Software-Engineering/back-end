package com.example.dssw.persistence;

import com.example.dssw.model.ReportDeclarationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.example.dssw.dto.MapDTO;
import com.example.dssw.model.RecycleBinEntity;
import com.example.dssw.model.ReportDeclarationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ReportDeclarationRepository extends JpaRepository<ReportDeclarationEntity,Long> {
    List<ReportDeclarationEntity> findByUser_IdAndType(Long userId, String type);
    Optional<ReportDeclarationEntity> findById(Long id);


}
