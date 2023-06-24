package com.example.ecommerce.repository;

import com.example.ecommerce.entity.ProductSize;
import com.example.ecommerce.model.response.ProductSizeResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author caodinh
 */
public interface ProductSizeRepository extends JpaRepository<ProductSize, String> {

    @Query("select new com.example.ecommerce.model.response.ProductSizeResponse(pc.size.id,pc.size.ten) from ProductSize pc where pc.chiTietSP.id = :id")
    List<ProductSizeResponse> getAllByCtspID(@Param("id") String id);

    @Modifying
    @Query(value = "delete from ProductSize pz where pz.chiTietSP.id = :proId")
    void deleteByCtspId(@Param("proId") String id);

}
