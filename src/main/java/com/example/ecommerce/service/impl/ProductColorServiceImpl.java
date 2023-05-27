package com.example.ecommerce.service.impl;

import com.example.ecommerce.model.response.ProductColorResponse;
import com.example.ecommerce.model.response.ProductSizeResponse;
import com.example.ecommerce.repository.ProductColorRepository;
import com.example.ecommerce.service.ProductColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
