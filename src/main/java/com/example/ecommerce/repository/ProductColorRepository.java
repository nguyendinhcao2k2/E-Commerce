package com.example.ecommerce.repository;

import com.example.ecommerce.entity.ProductColor;
import com.example.ecommerce.model.response.ProductColorResponse;
import com.example.ecommerce.model.response.SanPhamResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author caodinh
 */
public interface ProductColorRepository extends JpaRepository<ProductColor,String> {

    @Query("select new com.example.ecommerce.model.response.ProductColorResponse(pc.mauSac.id,pc.mauSac.ten) from ProductColor pc where pc.chiTietSP.id = :id")
    List<ProductColorResponse> getAllByCtspID(@Param("id") String id);

    @Query("SELECT new com.example.ecommerce.model.response.SanPhamResponse(pc.chiTietSP.id, pc.chiTietSP.tenSP, pc.chiTietSP.Img, pc.chiTietSP.giaBan, '') " +
            "FROM ProductColor pc " +
            "WHERE (:color IS NULL OR pc.mauSac.ten = :color) " +
            "AND (:minPrice IS NULL OR pc.chiTietSP.giaBan BETWEEN :minPrice AND :maxPrice) " +
            "AND (:greaterThanPrice IS NULL OR pc.chiTietSP.giaBan > :greaterThanPrice) " +
            "GROUP BY pc.chiTietSP.id")
    List<SanPhamResponse> filterProduct(@Param("color") String color,
                                        @Param("minPrice") BigDecimal minPrice,
                                        @Param("maxPrice") BigDecimal maxPrice,
                                        @Param("greaterThanPrice") BigDecimal greaterThanPrice);

}
