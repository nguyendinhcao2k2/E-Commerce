package com.example.ecommerce.repository;

import com.example.ecommerce.entity.GioHang;
import com.example.ecommerce.model.response.HoaDonResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author caodinh
 */
public interface GioHangRepository extends JpaRepository<GioHang, String> {

    @Query(value = "SELECT MAX(CAST(SUBSTRING(ma, 5) AS UNSIGNED)) FROM gio_hang", nativeQuery = true)
    String getMaxMaGioHang();

    @Query("select new com.example.ecommerce.model.response.HoaDonResponse(gh.id,gh.tenNguoiNhan,gh.sdt,gh.email,gh.diaChi,gh.hoaDon.tinhTrang) from GioHang gh where gh.hoaDon.tinhTrang = 'PROCESSING'")
    List<HoaDonResponse> getHoaDonByTrangThai();

}
