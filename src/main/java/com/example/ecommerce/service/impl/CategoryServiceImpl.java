package com.example.ecommerce.service.impl;

import com.example.ecommerce.model.response.CategoryResponse;
import com.example.ecommerce.repository.CategoryRepository;
import com.example.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author caodinh
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryResponse> getAllCategory() {
        return categoryRepository.getAllCategory();
    }

    @Override
    public Integer countCategory() {
        return categoryRepository.countCategory();
    }
}
