package com.example.ecommerce.service;

import com.example.ecommerce.model.response.CategoryResponse;

import java.util.List;

/**
 * @author caodinh
 */
public interface CategoryService {

    List<CategoryResponse> getAllCategory();

    Integer countCategory();

}
