package com.example.ecommerce.controller;

import com.example.ecommerce.model.response.SanPhamResponse;
import com.example.ecommerce.service.CategoryService;
import com.example.ecommerce.service.ChiTietSanPhamService;
import com.example.ecommerce.service.ProductColorService;
import com.example.ecommerce.service.ProductSizeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @author caodinh
 */
@Controller
@RequestMapping("/api/user")
public class ProductController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ChiTietSanPhamService chiTietSanPhamService;

    @Autowired
    private ProductSizeService productSizeService;
    @Autowired
    private ProductColorService productColorService;

    @Autowired
    private HttpSession session;


    @GetMapping("/products")
    public String getAllProducts(Model model) {
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("detailProducts", chiTietSanPhamService.getAllSanPham());
        return "shop";
    }

    @GetMapping("/product-category/{id}")
    public String getAllProductsByCateID(Model model, @PathVariable("id") String id) {
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("detailProducts", chiTietSanPhamService.getAllSanPhamByCateId(id));
        return "shop";
    }

    @GetMapping("/product-season/{id}")
    public String getAllProductsBySeasonID(Model model, @PathVariable("id") String id) {
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("detailProducts", chiTietSanPhamService.getAllSanPhamBySeasonId(id));
        return "shop";
    }

    @GetMapping("/view-detail-product/{id}")
    public String getProductByID(Model model, @PathVariable("id") String id) {
        model.addAttribute("categories", categoryService.getAllCategory());
        SanPhamResponse sanPhamResponse = chiTietSanPhamService.getOneByID(id).get();
        model.addAttribute("detailProducts", sanPhamResponse);
        model.addAttribute("prColor", productColorService.getAllByCtspID(id));
        model.addAttribute("prSize", productSizeService.getAllByCtspID(id));
        addArrivedProduct(sanPhamResponse);
        return "detail";
    }


    public void addArrivedProduct(SanPhamResponse sanPhamResponse) {
        List<SanPhamResponse> sanPhamResponses = (List<SanPhamResponse>) session.getAttribute("arrivedCart");
        if (sanPhamResponses == null) {
            sanPhamResponses = new ArrayList<>();
            sanPhamResponses.add(sanPhamResponse);
            session.setAttribute("arrivedCart", sanPhamResponses);
        } else {
            for (int i = 0; i < sanPhamResponses.size(); i++) {
                if (!sanPhamResponses.get(i).getId().equals(sanPhamResponse.getId())) {
                    sanPhamResponses.add(sanPhamResponse);
                    if (sanPhamResponses.size() > 8) {
                        sanPhamResponses.remove(8);
                    }
                }
            }
        }
    }
}
