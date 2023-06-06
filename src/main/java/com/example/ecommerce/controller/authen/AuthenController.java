package com.example.ecommerce.controller.authen;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author caodinh
 */
@Controller
public class AuthenController {

    @RequestMapping(value = "/403")
    public String prohibitedAccess() {
        return "authen/403";
    }
}
