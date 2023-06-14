package com.example.ecommerce.controller;

import com.example.ecommerce.entity.User;
import com.example.ecommerce.sercurity.CustomAdminDetails;
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
        User user = (User) session.getAttribute("info");
        CustomAdminDetails customAdminDetails = (CustomAdminDetails) session.getAttribute("admin");
        if (user != null) {
            return "redirect:/api/user/home";
        } else if (customAdminDetails != null) {
            return "redirect:/api/admin/home";
        }
        return "login";
    }

    @GetMapping("/profile")
    public String getProfile() {
        User user = (User) session.getAttribute("info");
        if (user == null) {
            return "redirect:/api/user/home";
        }
        return "profile";
    }

    @GetMapping("/contact")
    public String getContact() {
        return "contact";
    }

}
