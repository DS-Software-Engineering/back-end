package com.example.dssw.persistence;

import com.example.dssw.model.SolveBoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SolveBoardRepository extends JpaRepository<SolveBoardEntity, Long> {

}
