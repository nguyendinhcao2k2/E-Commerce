package com.example.ecommerce.repository;

import com.example.ecommerce.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author caodinh
 */
public interface AdminRepository extends JpaRepository<Admin,String> {
}
