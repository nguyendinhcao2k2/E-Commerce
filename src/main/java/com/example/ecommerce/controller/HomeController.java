package com.example.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author caodinh
 */
@Controller
@RequestMapping("/api/user")
public class HomeController {

    @GetMapping("/home")
    public String viewHome(){
        return "trang-chu";
    }
}
