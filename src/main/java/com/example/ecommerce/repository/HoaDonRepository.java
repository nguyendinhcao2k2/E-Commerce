package com.example.ecommerce.repository;

import com.example.ecommerce.entity.HoaDon;
import com.example.ecommerce.model.response.MonthAmountResponse;
import com.example.ecommerce.model.response.ThuAmountResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author caodinh
 */
public interface HoaDonRepository extends JpaRepository<HoaDon, String> {

    @Query(value = "SELECT MAX(CAST(SUBSTRING(ma, 5) AS UNSIGNED)) FROM hoa_don", nativeQuery = true)
    String getMaxMaHoaDon();

    @Query("SELECT SUM(hd.totalAmount) FROM HoaDon hd WHERE hd.tinhTrang = 'SUCCESS'")
    BigDecimal getTotalPrice();

    @Query("SELECT count(hd.id) FROM  HoaDon hd where hd.tinhTrang  = 'SUCCESS'")
    int totalInvoice();

    @Query("SELECT count(hd.id) FROM  HoaDon hd where hd.tinhTrang  = 'CANCELLED'")
    int cancelInvoice();

    @Query("SELECT new com.example.ecommerce.model.response.ThuAmountResponse( FUNCTION('DAYNAME', hd.ngayThanhToan), SUM(hd.totalAmount)) " +
            "FROM HoaDon hd " +
            "WHERE FUNCTION('YEARWEEK', hd.ngayThanhToan, 1) = FUNCTION('YEARWEEK', CURRENT_DATE, 1) AND  hd.tinhTrang = 'SUCCESS' " +
            "GROUP BY FUNCTION('DAYNAME', hd.ngayThanhToan) order by hd.ngayThanhToan ")
    List<ThuAmountResponse> getThuAndAmount();

    @Query("SELECT new com.example.ecommerce.model.response.MonthAmountResponse( FUNCTION('DAY', hd.ngayThanhToan), SUM(hd.totalAmount)) " +
            "FROM HoaDon hd " +
            "WHERE FUNCTION('MONTH', hd.ngayThanhToan) = FUNCTION('MONTH', CURRENT_DATE) " +
            "AND FUNCTION('YEAR', hd.ngayThanhToan) = FUNCTION('YEAR', CURRENT_DATE)  AND  hd.tinhTrang = 'SUCCESS' " +
            "GROUP BY FUNCTION('DAY', hd.ngayThanhToan) order by hd.ngayThanhToan")
    List<MonthAmountResponse> getAmountByMonth();

    @Query("SELECT new com.example.ecommerce.model.response.ThuAmountResponse( FUNCTION('MONTHNAME', hd.ngayThanhToan), SUM(hd.totalAmount)) " +
            "FROM HoaDon hd " +
            "WHERE FUNCTION('YEAR', hd.ngayThanhToan) = FUNCTION('YEAR', CURRENT_DATE) and hd.tinhTrang = 'SUCCESS' " +
            "GROUP BY FUNCTION('MONTH', hd.ngayThanhToan) order by hd.ngayThanhToan")
    List<ThuAmountResponse> getAmountByYear();

}
