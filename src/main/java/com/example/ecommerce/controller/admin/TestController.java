package com.example.ecommerce.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author caodinh
 */
@Controller
@RequestMapping("api/admin")
public class TestController {

    @GetMapping("/home")
    public String home(){
        return "admin/pages/home";
    }
}
