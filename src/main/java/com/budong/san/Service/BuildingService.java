package com.budong.san.Service;

import com.budong.san.Domain.Building;
import com.budong.san.Repository.BuildingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class BuildingService {
    private final BuildingRepository buildingRepository;

    public BuildingService(BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
    }

    public Long join(Building building) {
        buildingRepository.save(building);
        return building.getBno();
    }

    public Long edit(Building building){
        buildingRepository.edit(building);
        return building.getBno();
    }

    public Building findByBno(Long bno){
        return buildingRepository.findByBno(bno);
    }

    public List<Building> findBuildings(){
        return buildingRepository.findAll();
    }

    public void deleteOne(Long bno){
        buildingRepository.deleteOne(bno);
    }

    public List<Building> findBySelection(String aptName, int aptSizeMin, int aptSizeMax, String aptTransactionType, int aptPriceMin, int aptPriceMax){
        return buildingRepository.findBySelection(aptName, aptSizeMin, aptSizeMax, aptTransactionType, aptPriceMin, aptPriceMax);
    }
}
