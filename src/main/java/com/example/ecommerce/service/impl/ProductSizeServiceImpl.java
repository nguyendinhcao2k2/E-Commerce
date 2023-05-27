package com.example.ecommerce.service.impl;

import com.example.ecommerce.model.response.ProductSizeResponse;
import com.example.ecommerce.repository.ProductSizeRepository;
import com.example.ecommerce.service.ProductSizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author caodinh
 */
@Service
public class ProductSizeServiceImpl implements ProductSizeService {

    @Autowired
    private ProductSizeRepository productSizeRepository;

    @Override
    public List<ProductSizeResponse> getAllByCtspID(String id) {
        return productSizeRepository.getAllByCtspID(id);
    }
}
