package com.example.dssw.persistence;

import com.example.dssw.model.ReportDeclarationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReportDeclarationRepository extends JpaRepository<ReportDeclarationEntity,Long> {

    Optional<ReportDeclarationEntity> findById(Long id);
}
