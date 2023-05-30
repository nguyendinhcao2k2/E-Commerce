package com.example.ecommerce.service.impl;

import com.example.ecommerce.model.response.ProductColorResponse;
import com.example.ecommerce.model.response.SanPhamResponse;
import com.example.ecommerce.repository.ProductColorRepository;
import com.example.ecommerce.service.ProductColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author caodinh
 */
@Service
public class ProductColorServiceImpl implements ProductColorService {

    @Autowired
    private ProductColorRepository productColorRepository;

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

}
