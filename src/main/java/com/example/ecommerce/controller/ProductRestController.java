package com.example.ecommerce.controller;

import com.example.ecommerce.service.ChiTietSanPhamService;
import com.example.ecommerce.service.ProductColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author caodinh
 */
@RestController
@RequestMapping("/api/user")
public class ProductRestController {

    @Autowired
    private ChiTietSanPhamService chiTietSanPhamService;

    @Autowired
    private ProductColorService productColorService;

    @GetMapping("/search-product")
    public ResponseEntity<?> searchProduct(@RequestParam("searchValue") String searchValue){
        return new ResponseEntity<>(chiTietSanPhamService.searchByName(searchValue), HttpStatus.OK);
    }

    @GetMapping("/filter-product")
    public ResponseEntity<?> filterColorAndPrice(@RequestParam("price") String price,@RequestParam("color") String color){
        return new ResponseEntity<>(productColorService.filterProduct(color,price), HttpStatus.OK);
    }

}
