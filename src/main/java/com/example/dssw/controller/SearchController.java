package com.example.dssw.controller;

import com.example.dssw.dto.ResponseDTO;
import com.example.dssw.service.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/search")
    public ResponseEntity<?> searchBins(@RequestParam String keyword, @RequestParam(required = false) String type) {
        ResponseDTO responseDTO=null;
        List<Object> result=null;

        // 1. 모든 쓰레기통일 경우
        if (type==null) {
            result = searchService.searchBins(keyword);
        }
        // 2. 재활용 정거장일 경우
        else if(type.equals("recycleBin")) {
            result = Collections.singletonList(searchService.searchRecycleBins(keyword));
        }
        // 3. 가로 휴지통의 담배꽁초,일반,음료컵,재활용 일 경우
        else {
            result = Collections.singletonList(searchService.searchGeneralBinsWithFilter(keyword, type));
        }

        responseDTO= ResponseDTO.builder().status(200).success(true).data(result).build();

        return ResponseEntity.ok().body(responseDTO);
    }
}
