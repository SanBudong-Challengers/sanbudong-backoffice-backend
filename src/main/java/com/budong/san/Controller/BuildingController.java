package com.budong.san.Controller;

import com.budong.san.Domain.Building;
import com.budong.san.Service.BuildingService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api(tags="Building Controller")
@Controller
public class BuildingController {
    private final BuildingService buildingService;

    @Autowired
    public BuildingController(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @GetMapping("/")
    public String home(Model model){
        List<Building> buildings = buildingService.findBuildings();
        model.addAttribute("buildings", buildings);
        return "home";
    }

    @GetMapping("/option")
    public String option(@RequestParam(value = "aptName", required = false) String aptName,
                         @RequestParam(value = "aptSizeMin", required = false) Integer aptSizeMin,
                         @RequestParam(value = "aptSizeMax", required = false) Integer aptSizeMax,
                         @RequestParam(value = "aptTransactionType", required = false) String aptTransactionType,
                         @RequestParam(value = "aptPriceMin", required = false) Integer aptPriceMin,
                         @RequestParam(value = "aptPriceMax", required = false) Integer aptPriceMax, Model model){

        if(aptName == null)aptName = "";
        if(aptSizeMin == null)aptSizeMin = -1;
        if(aptSizeMax == null)aptSizeMax = 1000000;
        if(aptTransactionType == null)aptTransactionType = "";
        if(aptPriceMin == null)aptPriceMin = -1;
        if(aptPriceMax == null)aptPriceMax = 1000000;

        List<Building> buildings = buildingService.findBySelection(aptName, aptSizeMin.intValue(), aptSizeMax.intValue(), aptTransactionType, aptPriceMin.intValue(), aptPriceMax.intValue());
        model.addAttribute("buildings", buildings);

        return "home";
    }

    @GetMapping("/add")
    public String addForm(){
        return "addForm";
    }

    @PostMapping("/add")
    public String add(BuildingForm buildingForm) {
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
        return "redirect:/";
    }

    @GetMapping("/{bno}/edit")
    public String editForm(@PathVariable("bno") Long bno, Model model){
        Building building = buildingService.findByBno(bno);
        model.addAttribute(building);

        return "editForm";
    }

    @PutMapping("/{bno}/edit")
    public String edit(@PathVariable("bno") Long bno, BuildingForm buildingForm) {
        Building building = buildingService.findByBno(bno);

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

        return "redirect:/";
    }

    @DeleteMapping("/{bno}/delete")
    public String delete(@PathVariable("bno") Long bno){
        buildingService.deleteOne(bno);
        return "redirect:/";
    }
}
