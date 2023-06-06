package com.example.ecommerce.repository;

import com.example.ecommerce.entity.GioHangChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author caodinh
 */
public interface GioHangChiTietRepository extends JpaRepository<GioHangChiTiet,String> {
    @Query(value = "SELECT MAX(CAST(SUBSTRING(ma, 5) AS UNSIGNED)) FROM gio_hang_chi_tiet", nativeQuery = true)
    String getMaxMaGioHang();
}
