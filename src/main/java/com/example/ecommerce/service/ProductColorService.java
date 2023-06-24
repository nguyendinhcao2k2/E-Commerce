package com.example.ecommerce.service;

import com.example.ecommerce.model.response.ProductColorResponse;
import com.example.ecommerce.model.response.ProductSizeResponse;
import com.example.ecommerce.model.response.SanPhamResponse;

import java.util.List;

/**
 * @author caodinh
 */
public interface ProductColorService {

    List<ProductColorResponse> getAllByCtspID(String id);

    List<SanPhamResponse> filterProduct(String color, String price);

    boolean save(String proId,List<String> listColors);

}
