package com.example.ecommerce.service;

import com.example.ecommerce.model.response.ProductColorResponse;
import com.example.ecommerce.model.response.ProductSizeResponse;

import java.util.List;

/**
 * @author caodinh
 */
public interface ProductColorService {

    List<ProductColorResponse> getAllByCtspID(String id);

}
