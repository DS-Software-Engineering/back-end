package com.example.dssw.persistence;

import com.example.dssw.model.SolveBoardEntity;
import com.example.dssw.model.SolveBoardImagesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SolveBoardImagesRepository extends JpaRepository<SolveBoardImagesEntity, Long> {

    List<SolveBoardImagesEntity> findBySolveBoard(SolveBoardEntity solveBoardEntity);

}
