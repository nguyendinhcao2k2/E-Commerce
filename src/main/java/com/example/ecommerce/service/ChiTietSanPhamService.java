package com.example.ecommerce.service;

import com.example.ecommerce.model.response.SanPhamResponse;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * @author caodinh
 */
public interface ChiTietSanPhamService {

    List<SanPhamResponse> getAllSanPham();

    List<SanPhamResponse> getAllSanPhamByCateId(String id);

    List<SanPhamResponse> getAllSanPhamBySeasonId(String id);

    Optional<SanPhamResponse> getOneByID(String id);

    List<SanPhamResponse> searchByName(String tenSP);


}
