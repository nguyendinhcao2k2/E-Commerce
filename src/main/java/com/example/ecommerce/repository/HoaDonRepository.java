package com.example.ecommerce.repository;

import com.example.ecommerce.entity.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author caodinh
 */
public interface HoaDonRepository extends JpaRepository<HoaDon,String> {

    @Query(value = "SELECT MAX(CAST(SUBSTRING(ma, 5) AS UNSIGNED)) FROM hoa_don", nativeQuery = true)
    String getMaxMaHoaDon();

}
