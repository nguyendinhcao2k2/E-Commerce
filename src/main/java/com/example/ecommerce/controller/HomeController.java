package com.example.ecommerce.controller;

import com.example.ecommerce.entity.GioHang;
import com.example.ecommerce.entity.GioHangChiTiet;
import com.example.ecommerce.service.CategoryService;
import com.example.ecommerce.service.SeasonService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

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
    public String viewHome(Model model, HttpSession session) {
        GioHang gioHang = (GioHang) session.getAttribute("carts");
        if (gioHang == null) {
            gioHang = new GioHang();
            List<GioHangChiTiet> gioHangChiTietList = new ArrayList<>();
            gioHang.setChiTietGioHang(gioHangChiTietList);
            session.setAttribute("carts", gioHang);
        }
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("seasons", seasonService.getAllSeason());
        return "trang-chu";
    }

}
