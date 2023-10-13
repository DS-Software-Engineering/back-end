package com.example.dssw.service;

import com.example.dssw.model.GeneralBinEntity;
import com.example.dssw.model.RecycleBinEntity;
import com.example.dssw.persistence.GeneralBinRepository;
import com.example.dssw.persistence.RecycleBinRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class SearchService {

    @Autowired
    private GeneralBinRepository generalBinRepository;
    @Autowired
    private RecycleBinRepository recycleBinRepository;

    public List<Object> searchBins(String keyword) {
        List<GeneralBinEntity> generalBins = generalBinRepository.searchGeneralBins(keyword);
        List<RecycleBinEntity> recycleBins = recycleBinRepository.searchRecycleBins(keyword);

        List<Object> combinedResults = new ArrayList<>();
        combinedResults.addAll(generalBins);
        combinedResults.addAll(recycleBins);

        return combinedResults;
    }

    public List<RecycleBinEntity> searchRecycleBins(String keyword) {
        return recycleBinRepository.searchRecycleBins(keyword);
    }

    public List<GeneralBinEntity> searchGeneralBinsWithFilter(String keyword, String type) {

        List<GeneralBinEntity> generalBinsFiltered = new ArrayList<>();
        List<GeneralBinEntity> generalBins = generalBinRepository.searchGeneralBins(keyword);

        for (GeneralBinEntity generalBin : generalBins) {
            if (type.equals("general")) {
                if (generalBin.isType_general()) {
                    generalBinsFiltered.add(generalBin);
                }
            }
            else if (type.equals("cb")) {
                if (generalBin.isType_cb()) {
                    generalBinsFiltered.add(generalBin);
                }
            }
            else if (type.equals("drink")) {
                if (generalBin.isType_drink()) {
                    generalBinsFiltered.add(generalBin);
                }
            }
            else if (type.equals("recycle")) {
                if (generalBin.isType_recycle()) {
                    generalBinsFiltered.add(generalBin);
                }
            }
            else return null;
        }

        return generalBinsFiltered;
    }
}
