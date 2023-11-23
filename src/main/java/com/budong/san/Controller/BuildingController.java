package com.budong.san.Controller;

import com.budong.san.Domain.Building;
import com.budong.san.Service.BuildingService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api(tags="Building Controller")
@RestController
public class BuildingController {
    private final BuildingService buildingService;

    @Autowired
    public BuildingController(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @GetMapping("/")
    public List<Building> home(){
        List<Building> buildings = buildingService.findBuildings();
        return buildings;
    }

    @GetMapping("/find/{bno}")
    public Building find(@PathVariable("bno") Long bno){
        Building building = buildingService.findByBno(bno);
        return building;
    }

    @GetMapping("/option")
    public List<Building> option(@RequestParam(value = "aptName", required = false) String aptName,
                         @RequestParam(value = "aptSizeMin", required = false) Integer aptSizeMin,
                         @RequestParam(value = "aptSizeMax", required = false) Integer aptSizeMax,
                         @RequestParam(value = "aptTransactionType", required = false) String aptTransactionType,
                         @RequestParam(value = "aptPriceMin", required = false) Integer aptPriceMin,
                         @RequestParam(value = "aptPriceMax", required = false) Integer aptPriceMax){

        if(aptName == null)aptName = "";
        if(aptSizeMin == null)aptSizeMin = -1;
        if(aptSizeMax == null)aptSizeMax = 1000000;
        if(aptTransactionType == null)aptTransactionType = "";
        if(aptPriceMin == null)aptPriceMin = -1;
        if(aptPriceMax == null)aptPriceMax = 1000000;

        List<Building> buildings = buildingService.findBySelection(aptName, aptSizeMin.intValue(), aptSizeMax.intValue(), aptTransactionType, aptPriceMin.intValue(), aptPriceMax.intValue());

        return buildings;
    }

    @PostMapping("/add")
    public ResponseEntity add(BuildingForm buildingForm) {
        Building building = new Building();

        building.setAptName(buildingForm.getAptName());
        building.setAptDong(buildingForm.getAptDong());
        building.setAptHo(buildingForm.getAptHo());
        building.setAptType(buildingForm.getAptType());
        building.setAptSize(buildingForm.getAptSize());
        building.setAptDirection(buildingForm.getAptDirection());
        building.setAptTransactionType(buildingForm.getAptTransactionType());
        building.setAptPrice(buildingForm.getAptPrice());
        building.setAptOption(buildingForm.getAptOption());
        building.setAptNote(buildingForm.getAptNote());
        building.setOwnerName(buildingForm.getOwnerName());
        building.setOwnerPhone(buildingForm.getOwnerPhone());
        building.setOwnerMobileCarrier(buildingForm.getOwnerMobileCarrier());

        buildingService.join(building);
        return ResponseEntity.ok().body("add success");
    }

    @PutMapping("/{bno}/edit")
    public Building edit(@PathVariable("bno") Long bno, BuildingForm buildingForm) {
        Building building = buildingService.findByBno(bno);

        if(buildingForm.getAptName() == null)
            building.setAptName(building.getAptName());
        else
            building.setAptName(buildingForm.getAptName());
        building.setAptDong(buildingForm.getAptDong());
        building.setAptHo(buildingForm.getAptHo());
        building.setAptType(buildingForm.getAptType());
        building.setAptSize(buildingForm.getAptSize());
        building.setAptDirection(buildingForm.getAptDirection());
        building.setAptTransactionType(buildingForm.getAptTransactionType());
        building.setAptPrice(buildingForm.getAptPrice());
        building.setAptOption(buildingForm.getAptOption());
        building.setAptNote(buildingForm.getAptNote());
        building.setOwnerName(buildingForm.getOwnerName());
        building.setOwnerPhone(buildingForm.getOwnerPhone());
        building.setOwnerMobileCarrier(buildingForm.getOwnerMobileCarrier());

        buildingService.edit(building);

        return building;
    }

    @DeleteMapping("/{bno}/delete")
    public ResponseEntity delete(@PathVariable("bno") Long bno){
        buildingService.deleteOne(bno);

        return ResponseEntity.ok().body("delete success");
    }
}
