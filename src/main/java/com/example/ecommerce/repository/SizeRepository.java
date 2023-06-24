package com.example.ecommerce.repository;

import com.example.ecommerce.entity.Size;
import com.example.ecommerce.model.response.SizeResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author caodinh
 */
public interface SizeRepository extends JpaRepository<Size, String> {
    @Query("select new com.example.ecommerce.model.response.SizeResponse(s.id,s.ten) from Size  s where s.type = 'CLOTHING_SIZE'")
    List<SizeResponse> getByTypeClothing();

    @Query("select new com.example.ecommerce.model.response.SizeResponse(s.id,s.ten) from Size  s where s.type = 'SHOE_SIZE'")
    List<SizeResponse> getByTypeShoe();
}
