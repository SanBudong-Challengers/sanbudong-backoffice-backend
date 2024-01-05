package com.budong.san.Controller;

import com.budong.san.Domain.AptDropdown;
import com.budong.san.Service.AptdropdownService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.util.List;

@Api(tags = "AptDropdown Controller")
@RestController
public class AptDropdownController {
    private final AptdropdownService aptdropdownService;

    @Value("${cookie.key}")
    private String cookieKey;

    @Autowired
    public AptDropdownController(AptdropdownService aptdropdownService) {
        this.aptdropdownService = aptdropdownService;
    }

    @PostMapping("/addApt")
    private AptDropdown addApt(@CookieValue(value = "myCookie", required = false) Cookie cookie,
                               @RequestParam String aptName) {
        if (cookie == null) return null;
        else if (cookie.getValue().toString().equals(cookieKey)) {
            AptDropdown aptDropdown = new AptDropdown();
            aptDropdown.setAptName(aptName);
            aptdropdownService.join(aptDropdown);
            return aptDropdown;
        } else return null;
    }

    @GetMapping("/getApt")
    private List<AptDropdown> getApt(@CookieValue(value = "myCookie", required = false) Cookie cookie) {
        if (cookie == null) return null;
        else if (cookie.getValue().toString().equals(cookieKey)) {
            List<AptDropdown> aptDropdownList = aptdropdownService.findAparts();
            return aptDropdownList;
        } else return null;
    }
}
