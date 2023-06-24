package com.example.ecommerce.service;

import com.example.ecommerce.model.response.ProductSizeResponse;

import java.util.List;

/**
 * @author caodinh
 */
public interface ProductSizeService {

    List<ProductSizeResponse> getAllByCtspID(String id);

    boolean save(String proId,List<String> shoeSize,List<String> clothingSize);

}
