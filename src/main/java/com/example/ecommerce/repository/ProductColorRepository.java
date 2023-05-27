package com.example.ecommerce.repository;

import com.example.ecommerce.entity.ProductColor;
import com.example.ecommerce.model.response.ProductColorResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author caodinh
 */
public interface ProductColorRepository extends JpaRepository<ProductColor,String> {

    @Query("select new com.example.ecommerce.model.response.ProductColorResponse(pc.mauSac.id,pc.mauSac.ten) from ProductColor pc where pc.chiTietSP.id = :id")
    List<ProductColorResponse> getAllByCtspID(@Param("id") String id);

}
