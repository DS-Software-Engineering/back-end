package com.example.dssw.controller;

import com.example.dssw.dto.MapDTO;
import com.example.dssw.dto.ResponseDTO;
import com.example.dssw.dto.UserDTO;
import com.example.dssw.model.GeneralBinEntity;
import com.example.dssw.model.RecycleBinEntity;
import com.example.dssw.model.UserEntity;
import com.example.dssw.persistence.GeneralBinRepository;
import com.example.dssw.persistence.RecycleBinRepository;
import com.example.dssw.service.GeneralBinService;
import com.example.dssw.service.RecycleBinService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/map")
public class MapController {
    @Autowired
    GeneralBinService generalBinService;
    @Autowired
    RecycleBinService recycleBinService;


    @GetMapping
    public ResponseEntity<?> getBinMap(@RequestParam(required = false) String type)
    {

            ResponseDTO responseDTO=null;
            List<MapDTO> result=null;

            // 1. 모든 쓰레기통일 경우
            if(type==null){
                result=generalBinService.getAll(); // 가로휴지통 다 가져옴
                result.addAll(recycleBinService.getAll()); // 재활용 정거장 다 가져옴
            }

            // 2. 재활용 정거장일 경우
            else if(type.equals("recycleBin")){ result=recycleBinService.getAll(); }

            // 3. 가로 휴지통의 재활용,담배꽁초,일반, 음료컵 일 경우
            else{ result=generalBinService.getBinByType(type); }

            responseDTO= ResponseDTO.<MapDTO>builder().status(200).success(true).data(result).build();

            return ResponseEntity.ok().body(responseDTO);

    }

}
