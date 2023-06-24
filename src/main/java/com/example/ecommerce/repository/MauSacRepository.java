package com.example.ecommerce.repository;

import com.example.ecommerce.entity.MauSac;
import com.example.ecommerce.model.response.MauSacResponse;
import com.example.ecommerce.model.response.SizeResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author caodinh
 */
public interface MauSacRepository extends JpaRepository<MauSac, String> {

    @Query("select new com.example.ecommerce.model.response.MauSacResponse(s.id,s.ten) from MauSac  s")
    List<MauSacResponse> getMauSac();

}
