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
@Controller
public class AptDropdownController {
    private final AptdropdownService aptdropdownService;

    @Autowired
    public AptDropdownController(AptdropdownService aptdropdownService) {
        this.aptdropdownService = aptdropdownService;
    }

    @PostMapping("/addApt")
    @ResponseBody
    private AptDropdown addApt(@RequestParam String aptName){
        AptDropdown aptDropdown = new AptDropdown();
        aptDropdown.setAptName(aptName);
        aptdropdownService.join(aptDropdown);
        return aptDropdown;
    }

    @GetMapping("/getApt")
    @ResponseBody
    private List<AptDropdown> getApt(){
        List<AptDropdown> aptDropdownList = aptdropdownService.findAparts();
//        model.addAttribute("aptDropdown",aptDropdownList);
//        System.out.println(aptDropdownList);
        return aptDropdownList;
    }
}
