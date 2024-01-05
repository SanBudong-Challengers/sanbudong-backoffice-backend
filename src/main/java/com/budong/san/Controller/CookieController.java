package com.budong.san.Controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Api(tags="Cookie Controller")
@Controller
public class CookieController {

    @PostMapping("/login")
    public void makeCookie(HttpServletResponse response , @RequestParam(value="password") String password){
        UUID uuid3 = UUID.nameUUIDFromBytes(password.getBytes());

        Cookie cookie = new Cookie("myCookie", uuid3.toString());
        cookie.setMaxAge(12 * 60 * 60);
        cookie.setPath("/");

        response.addCookie(cookie);
    }

    @PostMapping("/logout")
    public void deleteCookie(HttpServletResponse response){
        Cookie cookie = new Cookie("myCookie", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
