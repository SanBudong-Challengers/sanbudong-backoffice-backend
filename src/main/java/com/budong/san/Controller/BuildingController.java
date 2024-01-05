package com.budong.san.Controller;

import com.budong.san.Domain.Building;
import com.budong.san.Service.BuildingService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api(tags = "Building Controller")
@RestController
public class BuildingController {

    @Value("${cookie.key}")
    private String cookieKey;

    private final BuildingService buildingService;

    @Autowired
    public BuildingController(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @GetMapping("/")
    public List<Building> home(@CookieValue(value = "myCookie", required = false) Cookie cookie,
                               @RequestParam(value = "page", required = false) Integer page,
                               HttpServletResponse response) {
        if (cookie == null) return null;
        else if (cookie.getValue().toString().equals(cookieKey)) {
            if (page == null) page = 0;
            page = page * 10;
//        PageRequest pageRequest = PageRequest.of(page,10);
            List<Building> buildings = buildingService.findBuildings(page);
            return buildings;
        } else {
            return null;
        }

    }

    @GetMapping("/find/{bno}")
    public Building find(@CookieValue(value = "myCookie", required = false) Cookie cookie,
                         @PathVariable("bno") Long bno) {
        if (cookie == null) return null;
        else if (cookie.getValue().toString().equals(cookieKey)) {
            Building building = buildingService.findByBno(bno);
            return building;
        } else return null;
    }

    @GetMapping("/option")
    public List<Building> option(@CookieValue(value = "myCookie", required = false) Cookie cookie,
                                 @RequestParam(value = "aptName", required = false) String aptName,
                                 @RequestParam(value = "aptSizeMin", required = false) Double aptSizeMin,
                                 @RequestParam(value = "aptSizeMax", required = false) Double aptSizeMax,
                                 @RequestParam(value = "aptTransactionType", required = false) String aptTransactionType,
                                 @RequestParam(value = "aptPriceMin", required = false) Integer aptPriceMin,
                                 @RequestParam(value = "aptPriceMax", required = false) Integer aptPriceMax,
                                 @RequestParam(value = "page", required = false) Integer page) {

        if (cookie == null) return null;
        else if (cookie.getValue().toString().equals(cookieKey)) {
            if (aptName == null) aptName = "";
            if (aptSizeMin == null) aptSizeMin = -1.0;
            if (aptSizeMax == null) aptSizeMax = 1000000.0;
            if (aptTransactionType == null) aptTransactionType = "";
            if (aptPriceMin == null) aptPriceMin = -1;
            if (aptPriceMax == null) aptPriceMax = 1000000;
            if (page == null) page = 0;
            page = page * 10;

            List<Building> buildings = buildingService.findBySelection(aptName, aptSizeMin.doubleValue(), aptSizeMax.doubleValue(), aptTransactionType, aptPriceMin.intValue(), aptPriceMax.intValue(), page);

            return buildings;
        } else return null;
    }

    @PostMapping("/add")
    public ResponseEntity add(@CookieValue(value = "myCookie", required = false) Cookie cookie,
                              BuildingForm buildingForm) {
        if (cookie == null) return null;
        else if (cookie.getValue().toString().equals(cookieKey)) {
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
        } else return null;
    }

    @GetMapping("/count")
    public Long count(@CookieValue(value = "myCookie", required = false) Cookie cookie) {
        if (cookie == null) return null;
        else if (cookie.getValue().toString().equals(cookieKey)) {
            return buildingService.count();
        } else return null;
    }

    @PutMapping("/{bno}/edit")
    public Building edit(@CookieValue(value = "myCookie", required = false) Cookie cookie,
                         @PathVariable("bno") Long bno, BuildingForm buildingForm) {
        if (cookie == null) return null;
        else if (cookie.getValue().toString().equals(cookieKey)) {
            Building building = buildingService.findByBno(bno);

            if (buildingForm.getAptName() == null)
                building.setAptName(building.getAptName());
            else
                building.setAptName(buildingForm.getAptName());

            if (buildingForm.getAptDong() == null)
                building.setAptDong(building.getAptDong());
            else
                building.setAptDong(buildingForm.getAptDong());

            if (buildingForm.getAptHo() == null)
                building.setAptHo(building.getAptHo());
            else
                building.setAptHo(buildingForm.getAptHo());

            if (buildingForm.getAptType() == null)
                building.setAptType(building.getAptType());
            else
                building.setAptType(buildingForm.getAptType());

            if (buildingForm.getAptSize() == 0)
                building.setAptSize(building.getAptSize());
            else
                building.setAptSize(buildingForm.getAptSize());

            if (buildingForm.getAptDirection() == null)
                building.setAptDirection(building.getAptDirection());
            else
                building.setAptDirection(buildingForm.getAptDirection());

            if (buildingForm.getAptTransactionType() == null)
                building.setAptTransactionType(building.getAptTransactionType());
            else
                building.setAptTransactionType(buildingForm.getAptTransactionType());

            if (buildingForm.getAptPrice() == 0)
                building.setAptPrice(building.getAptPrice());
            else
                building.setAptPrice(buildingForm.getAptPrice());

            if (buildingForm.getAptOption() == null)
                building.setAptOption(building.getAptOption());
            else
                building.setAptOption(buildingForm.getAptOption());

            if (buildingForm.getAptNote() == null)
                building.setAptNote(building.getAptNote());
            else
                building.setAptNote(buildingForm.getAptNote());

            if (buildingForm.getOwnerName() == null)
                building.setOwnerName(building.getOwnerName());
            else
                building.setOwnerName(buildingForm.getOwnerName());

            if (buildingForm.getOwnerPhone() == null)
                building.setOwnerPhone(building.getOwnerPhone());
            else
                building.setOwnerPhone(buildingForm.getOwnerPhone());

            if (buildingForm.getOwnerMobileCarrier() == null)
                building.setOwnerMobileCarrier(building.getOwnerMobileCarrier());
            else
                building.setOwnerMobileCarrier(buildingForm.getOwnerMobileCarrier());

            buildingService.edit(building);

            return building;
        } else return null;
    }

    @DeleteMapping("/{bno}/delete")
    public ResponseEntity delete(@CookieValue(value = "myCookie", required = false) Cookie cookie,
                                 @PathVariable("bno") Long bno) {
        if (cookie == null) return null;
        else if (cookie.getValue().toString().equals(cookieKey)) {
            buildingService.deleteOne(bno);

            return ResponseEntity.ok().body("delete success");
        } else return null;
    }
}
