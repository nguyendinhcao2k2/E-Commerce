package com.example.ecommerce.repository;

import com.example.ecommerce.entity.GioHangChiTiet;
import com.example.ecommerce.model.response.GioHangChiTietResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author caodinh
 */
public interface GioHangChiTietRepository extends JpaRepository<GioHangChiTiet, String> {
    @Query(value = "SELECT MAX(CAST(SUBSTRING(ma, 5) AS UNSIGNED)) FROM gio_hang_chi_tiet", nativeQuery = true)
    String getMaxMaGioHang();

    @Query("select new com.example.ecommerce.model.response.GioHangChiTietResponse(ghct.chiTietSP.tenSP, ghct.color_name, ghct.sizeName, ghct.donGia, ghct.soLuong) from GioHangChiTiet ghct where ghct.gioHang.id = :id")
    List<GioHangChiTietResponse> getAllByIdGH(@Param("id") String id);

}
