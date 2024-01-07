package com.budong.san.Controller;

import com.budong.san.Domain.Building;
import com.budong.san.Service.BuildingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;

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
    public ResponseEntity home(@ApiIgnore @CookieValue(value = "myCookie", required = false) Cookie cookie,
                               @RequestParam(value = "page", required = false) Integer page,
                               HttpServletResponse response) {
        if (cookie == null || !cookie.getValue().toString().equals(cookieKey))
            return ResponseEntity.status(401).body("Unauthorized");

        if (page == null) page = 0;
        page = page * 10;
//        PageRequest pageRequest = PageRequest.of(page,10);
        List<Building> buildings = buildingService.findBuildings(page);
        return ResponseEntity.ok().body(buildings);

    }

    @GetMapping("/find/{bno}")
    public ResponseEntity find(@ApiIgnore @CookieValue(value = "myCookie", required = false) Cookie cookie,
                               @PathVariable("bno") Long bno) {
        if (cookie == null || !cookie.getValue().toString().equals(cookieKey))
            return ResponseEntity.status(401).body("Unauthorized");

        Building building = buildingService.findByBno(bno);
        return ResponseEntity.ok().body(building);
    }

    @GetMapping("/option")
    public ResponseEntity option(@ApiIgnore @CookieValue(value = "myCookie", required = false) Cookie cookie,
                                 @RequestParam(value = "aptName", required = false) String aptName,
                                 @RequestParam(value = "aptSizeMin", required = false) Double aptSizeMin,
                                 @RequestParam(value = "aptSizeMax", required = false) Double aptSizeMax,
                                 @RequestParam(value = "aptTransactionType", required = false) String aptTransactionType,
                                 @RequestParam(value = "aptPriceMin", required = false) Integer aptPriceMin,
                                 @RequestParam(value = "aptPriceMax", required = false) Integer aptPriceMax,
                                 @RequestParam(value = "page", required = false) Integer page) {
        if (cookie == null || !cookie.getValue().toString().equals(cookieKey))
            return ResponseEntity.status(401).body("Unauthorized");

        if (aptName == null) aptName = "";
        if (aptSizeMin == null) aptSizeMin = -1.0;
        if (aptSizeMax == null) aptSizeMax = 1000000.0;
        if (aptTransactionType == null) aptTransactionType = "";
        if (aptPriceMin == null) aptPriceMin = -1;
        if (aptPriceMax == null) aptPriceMax = 1000000;
        if (page == null) page = 0;
        page = page * 10;

        List<Building> buildings = buildingService.findBySelection(aptName, aptSizeMin.doubleValue(), aptSizeMax.doubleValue(), aptTransactionType, aptPriceMin.intValue(), aptPriceMax.intValue(), page);

        return ResponseEntity.ok().body(buildings);
    }

    @ApiImplicitParams(
            {
                    @ApiImplicitParam(
                            name = "aptName",
                            dataTypeClass = String.class,
                            required = true
                    ),
                    @ApiImplicitParam(
                            name = "aptDong",
                            dataTypeClass = String.class,
                            required = true
                    ),
                    @ApiImplicitParam(
                            name = "aptHo",
                            dataTypeClass = String.class,
                            required = true
                    ),
                    @ApiImplicitParam(
                            name = "aptSize",
                            dataTypeClass = Double.class,
                            example = "0.0",
                            required = true
                    ),
                    @ApiImplicitParam(
                            name = "aptTransactionType",
                            dataTypeClass = String.class,
                            required = true
                    ),
                    @ApiImplicitParam(
                            name = "aptPrice",
                            dataTypeClass = Integer.class,
                            example = "0",
                            required = true
                    ),
                    @ApiImplicitParam(
                            name = "ownerName",
                            dataTypeClass = String.class,
                            required = true
                    ),
                    @ApiImplicitParam(
                            name = "ownerPhone",
                            dataTypeClass = String.class,
                            required = true
                    ),
                    @ApiImplicitParam(
                            name = "ownerMobileCarrier",
                            dataTypeClass = String.class,
                            required = true
                    )
            }
    )
    @PostMapping("/add")
    public ResponseEntity add(@ApiIgnore @CookieValue(value = "myCookie", required = false) Cookie cookie,
                              BuildingForm buildingForm) {
        if (cookie == null || !cookie.getValue().toString().equals(cookieKey))
            return ResponseEntity.status(401).body("Unauthorized");

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

    @GetMapping("/count")
    public ResponseEntity count(@ApiIgnore @CookieValue(value = "myCookie", required = false) Cookie cookie) {
        if (cookie == null || !cookie.getValue().toString().equals(cookieKey))
            return ResponseEntity.status(401).body("Unauthorized");

        return ResponseEntity.ok().body(buildingService.count());

    }

    @PutMapping("/{bno}/edit")
    public ResponseEntity edit(@ApiIgnore @CookieValue(value = "myCookie", required = false) Cookie cookie,
                               @PathVariable("bno") Long bno, BuildingForm buildingForm) {
        if (cookie == null || !cookie.getValue().toString().equals(cookieKey))
            return ResponseEntity.status(401).body("Unauthorized");

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

        return ResponseEntity.ok().body(building);

    }

    @DeleteMapping("/{bno}/delete")
    public ResponseEntity delete(@ApiIgnore @CookieValue(value = "myCookie", required = false) Cookie cookie,
                                 @PathVariable("bno") Long bno) {
        if (cookie == null || !cookie.getValue().toString().equals(cookieKey))
            return ResponseEntity.status(401).body("Unauthorized");

        buildingService.deleteOne(bno);

        return ResponseEntity.ok().body("delete success");
    }
}
