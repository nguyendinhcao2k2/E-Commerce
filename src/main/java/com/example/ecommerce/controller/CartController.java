package com.example.ecommerce.controller;

import com.example.ecommerce.entity.GioHang;
import com.example.ecommerce.entity.GioHangChiTiet;
import com.example.ecommerce.service.CategoryService;
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
public class CartController {

    @Autowired
    HttpSession session;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/cart")
    public String cart(Model model) {
        GioHang gioHang = (GioHang) session.getAttribute("carts");
        Double sum = 0.00;
        if (gioHang == null) {
            gioHang = new GioHang();
            List<GioHangChiTiet> gioHangChiTietList = new ArrayList<>();
            gioHang.setChiTietGioHang(gioHangChiTietList);
            session.setAttribute("carts", gioHang);
        } else {
            for (GioHangChiTiet gioHangChiTiet : gioHang.getChiTietGioHang()) {
                sum += gioHangChiTiet.getDonGia().doubleValue() * gioHangChiTiet.getSoLuong();
            }
        }
        model.addAttribute("sumPrice", sum);
        model.addAttribute("categories", categoryService.getAllCategory());
        return "cart";
    }

    @GetMapping("/view-check-out")
    public String checkOut(Model model) {
        GioHang gioHang = (GioHang) session.getAttribute("carts");
        if (gioHang != null && gioHang.getChiTietGioHang().size() > 0) {
            Double sum = 0.00;
            if (gioHang == null) {
                gioHang = new GioHang();
                List<GioHangChiTiet> gioHangChiTietList = new ArrayList<>();
                gioHang.setChiTietGioHang(gioHangChiTietList);
                session.setAttribute("carts", gioHang);
            } else {
                for (GioHangChiTiet gioHangChiTiet : gioHang.getChiTietGioHang()) {
                    sum += gioHangChiTiet.getDonGia().doubleValue() * gioHangChiTiet.getSoLuong();
                }
            }
            model.addAttribute("sumPrice", sum);
            model.addAttribute("categories", categoryService.getAllCategory());
            return "check-out";

        } else {
            return "redirect:/api/user/products";
        }

    }
}
