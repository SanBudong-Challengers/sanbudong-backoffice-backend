package com.budong.san.Controller;

import com.budong.san.Domain.AptDropdown;
import com.budong.san.Service.AptdropdownService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags="AptDropdown Controller")
@RestController
public class AptDropdownController {
    private final AptdropdownService aptdropdownService;

    @Autowired
    public AptDropdownController(AptdropdownService aptdropdownService) {
        this.aptdropdownService = aptdropdownService;
    }

    @PostMapping("/addApt")
    private AptDropdown addApt(@RequestParam String aptName){
        AptDropdown aptDropdown = new AptDropdown();
        aptDropdown.setAptName(aptName);
        aptdropdownService.join(aptDropdown);
        return aptDropdown;
    }

    @GetMapping("/getApt")
    private List<AptDropdown> getApt(){
        List<AptDropdown> aptDropdownList = aptdropdownService.findAparts();
        return aptDropdownList;
    }
}
