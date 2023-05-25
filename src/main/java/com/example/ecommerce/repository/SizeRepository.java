package com.example.ecommerce.repository;

import com.example.ecommerce.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author caodinh
 */
public interface SizeRepository extends JpaRepository<Size,String> {
}
