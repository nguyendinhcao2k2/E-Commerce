package com.example.ecommerce.repository;

import com.example.ecommerce.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author caodinh
 */
public interface AdminRepository extends JpaRepository<Admin, String> {
    @Query("select am from Admin am where am.taiKhoan =:userName")
    Admin findByUsername(@Param("userName") String username);
}
