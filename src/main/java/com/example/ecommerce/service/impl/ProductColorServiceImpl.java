package com.example.ecommerce.service.impl;

import com.example.ecommerce.entity.ChiTietSanPham;
import com.example.ecommerce.entity.MauSac;
import com.example.ecommerce.entity.ProductColor;
import com.example.ecommerce.model.response.ProductColorResponse;
import com.example.ecommerce.model.response.SanPhamResponse;
import com.example.ecommerce.repository.ChiTietSanPhamRepository;
import com.example.ecommerce.repository.MauSacRepository;
import com.example.ecommerce.repository.ProductColorRepository;
import com.example.ecommerce.service.ProductColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author caodinh
 */
@Service
public class ProductColorServiceImpl implements ProductColorService {

    @Autowired
    private ProductColorRepository productColorRepository;

    @Autowired
    private ChiTietSanPhamRepository chiTietSanPhamRepository;

    @Autowired
    private MauSacRepository mauSacRepository;

    @Override
    public List<ProductColorResponse> getAllByCtspID(String id) {
        return productColorRepository.getAllByCtspID(id);
    }

    @Override
    public List<SanPhamResponse> filterProduct(String color, String price) {
        if (color.equals("all")) {
            color = null;
        }
        BigDecimal minPrice = null;
        BigDecimal maxPrice = null;
        BigDecimal greaterThanPrice = null;
        if (price.equals("400")) {
            greaterThanPrice = new BigDecimal("400");
        } else if (price.contains("-")) {
            String priceProducts[] = price.split("-");
            minPrice = new BigDecimal(priceProducts[0]);
            maxPrice = new BigDecimal(priceProducts[1]);
        }
        return productColorRepository.filterProduct(color, minPrice, maxPrice, greaterThanPrice);
    }

    @Override
    public boolean save(String proId, List<String> listColors) {
        List<ProductColor> productColors = new ArrayList<>();
        Optional<ChiTietSanPham> chiTietSanPham = chiTietSanPhamRepository.findById(proId);
        if (chiTietSanPham.isPresent()) {
            List<MauSac> mauSacs = mauSacRepository.findAllById(listColors);
            for (MauSac ms : mauSacs) {
                productColors.add(new ProductColor(null, chiTietSanPham.get(), ms));
            }
            productColorRepository.saveAll(productColors);
            return true;
        }
        return false;
    }

}
