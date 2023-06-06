package com.example.ecommerce.repository;

import com.example.ecommerce.entity.GioHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author caodinh
 */
public interface GioHangRepository extends JpaRepository<GioHang,String> {

    @Query(value = "SELECT MAX(CAST(SUBSTRING(ma, 5) AS UNSIGNED)) FROM gio_hang", nativeQuery = true)
    String getMaxMaGioHang();

}
