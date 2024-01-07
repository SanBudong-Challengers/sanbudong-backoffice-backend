package com.budong.san.Controller;

import com.budong.san.Domain.AptDropdown;
import com.budong.san.Service.AptdropdownService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.Cookie;
import java.util.List;

@Api(tags = "AptDropdown Controller")
@RestController
public class AptDropdownController {
    private final AptdropdownService aptDropdownService;

    @Value("${cookie.key}")
    private String cookieKey;

    @Autowired
    public AptDropdownController(AptdropdownService aptdropdownService) {
        this.aptDropdownService = aptdropdownService;
    }

    @PostMapping("/addApt")
    private ResponseEntity addApt(@ApiIgnore @CookieValue(value = "myCookie", required = false) Cookie cookie,
                                  @RequestParam String aptName) {
        if (cookie == null || !cookie.getValue().toString().equals(cookieKey))
            return ResponseEntity.status(401).body("Unauthorized");

        AptDropdown aptDropdown = new AptDropdown();
        aptDropdown.setAptName(aptName);
        aptDropdownService.join(aptDropdown);
        return ResponseEntity.ok().body(aptDropdown);
    }

    @GetMapping("/getApt")
    private ResponseEntity getApt(@ApiIgnore @CookieValue(value = "myCookie", required = false) Cookie cookie,
                                  @RequestParam(value = "page", required = false) Integer page) {
        if (cookie == null || !cookie.getValue().toString().equals(cookieKey))
            return ResponseEntity.status(401).body("Unauthorized");

        if (page == null) page = 0;
        page = page * 10;
        List<AptDropdown> aptDropdownList = aptDropdownService.findAparts(page);
        return ResponseEntity.ok().body(aptDropdownList);
    }

    @ApiIgnore
    @GetMapping("/{idApt}/getAptById")
    public ResponseEntity getAptById(@ApiIgnore @CookieValue(value = "myCookie", required = false) Cookie cookie,
                                     @RequestParam(value = "idApt", required = true) Long idApt) {
        if (cookie == null || !cookie.getValue().toString().equals(cookieKey))
            return ResponseEntity.status(401).body("Unauthorized");

        try {
            AptDropdown aptDropdown = aptDropdownService.findByIdApt(idApt);
            return ResponseEntity.ok().body(aptDropdown);
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Not Found");
        }
    }

    @PutMapping("/{idApt}/editApt")
    public ResponseEntity editApt(@ApiIgnore @CookieValue(value = "myCookie", required = false) Cookie cookie,
                                  @PathVariable(value = "idApt", required = true) Long idApt,
                                  @RequestParam(value = "aptName", required = true) String aptName) {
        if (cookie == null || !cookie.getValue().toString().equals(cookieKey))
            return ResponseEntity.status(401).body("Unauthorized");

        try {
            AptDropdown aptDropdown = aptDropdownService.edit(new AptDropdown(idApt, aptName));
            return ResponseEntity.ok().body(aptDropdown);
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Not Found");
        }
    }

    @DeleteMapping("/{idApt}/deleteApt")
    private ResponseEntity deleteApt(@ApiIgnore @CookieValue(value = "myCookie", required = false) Cookie cookie,
                                     @RequestParam(value = "idApt", required = true) Long idApt) {
        if (cookie == null || !cookie.getValue().toString().equals(cookieKey))
            return ResponseEntity.status(401).body("Unauthorized");

        try {
            aptDropdownService.deleteApart(idApt);
            return ResponseEntity.ok().body("delete success");
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Not Found");
        }
    }
}
