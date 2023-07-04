package com.example.ecommerce.controller.admin;

import com.example.ecommerce.service.CategoryService;
import com.example.ecommerce.service.ChiTietSanPhamService;
import com.example.ecommerce.service.GioHangService;
import com.example.ecommerce.service.MauSacService;
import com.example.ecommerce.service.SeasonService;
import com.example.ecommerce.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

/**
 * @author caodinh
 */
@Controller
@RequestMapping("api/admin")
public class AdminController {

    @Autowired
    private ChiTietSanPhamService chiTietSanPhamService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SeasonService seasonService;

    @Autowired
    private SizeService sizeService;

    @Autowired
    private MauSacService mauSacService;

    @Autowired
    private GioHangService gioHangService;

    @GetMapping("/home")
    public String home(Model model) throws IOException {
        model.addAttribute("amountProduct", chiTietSanPhamService.countAllProduct());
        model.addAttribute("amountCategory", categoryService.countCategory());
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("seasons", seasonService.getAllSeason());
        model.addAttribute("shoeSize", sizeService.getByTypeShoe());
        model.addAttribute("clothingSize", sizeService.getByTypeClothing());
        model.addAttribute("mauSac", mauSacService.getMauSac());
        model.addAttribute("products", chiTietSanPhamService.getTop5());
        return "admin/index";
    }

    @GetMapping("/product")
    public String viewProduct(Model model) {
        model.addAttribute("products", chiTietSanPhamService.getAllSanPham());
        return "admin/product";
    }

    @GetMapping("/detail/{id}")
    public String detailProduct(Model model, @PathVariable("id") String id) {
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("seasons", seasonService.getAllSeason());
        model.addAttribute("product", chiTietSanPhamService.getOneByID(id).get());
        return "admin/detail";
    }

    @GetMapping("/profile")
    public String getProfile() {
        return "admin/profile";
    }

    @GetMapping("/hoa-don")
    public String getHoaDon(Model model) {
        model.addAttribute("hoaDon", gioHangService.getHoaDonByTrangThai());
        return "admin/hoadon";
    }

    @GetMapping("/thong-ke")
    public String thongKe(Model model) {

        return "admin/thongke";
    }

}
