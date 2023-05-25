package com.example.ecommerce.repository;

import com.example.ecommerce.entity.ProductSize;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author caodinh
 */
public interface ProductSizeRepository extends JpaRepository<ProductSize,String> {
}
