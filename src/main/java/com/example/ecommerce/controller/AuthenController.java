package com.example.ecommerce.controller;

import com.example.ecommerce.sercurity.CustomOAuth2User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * @author caodinh
 */
@Controller
public class AuthenController {

    @Autowired
    private HttpSession session;

    @GetMapping("/403")
    public String process403() {
        return "authen/403";
    }

    @GetMapping("/login-page")
    public String login() {
        CustomOAuth2User oauth2 = (CustomOAuth2User) session.getAttribute("info");
        if(oauth2 != null){
            return "redirect:/api/user/home";
        }
        return "login";
    }

    @GetMapping("/profile")
    public String getProfile() {
        return "profile";
    }

}
