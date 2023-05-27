package com.example.ecommerce.controller;

import com.example.ecommerce.service.CategoryService;
import com.example.ecommerce.service.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author caodinh
 */
@Controller
@RequestMapping("/api/user")
public class HomeController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SeasonService seasonService;

    @GetMapping("/home")
    public String viewHome(Model model){
        model.addAttribute("categories",categoryService.getAllCategory());
        model.addAttribute("seasons",seasonService.getAllSeason());
        return "trang-chu";
    }

}
