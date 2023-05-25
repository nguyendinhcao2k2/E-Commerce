package com.example.ecommerce.repository;

import com.example.ecommerce.entity.Category;
import com.example.ecommerce.model.CategoryResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author caodinh
 */
public interface CategoryRepository extends JpaRepository<Category,String> {
    @Query("select new com.example.ecommerce.model.CategoryResponse(ctsp.category.id,ctsp.category.ten,ctsp.category.img,count (ctsp.id)) from ChiTietSanPham as ctsp group by ctsp.category.id")
    List<CategoryResponse> getAllCategory();
}
