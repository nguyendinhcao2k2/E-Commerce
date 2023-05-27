package com.example.ecommerce.repository;


import com.example.ecommerce.entity.ChiTietSanPham;
import com.example.ecommerce.model.response.SanPhamResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * @author caodinh
 */
public interface ChiTietSanPhamRepository extends JpaRepository<ChiTietSanPham, String> {

    @Query("select new com.example.ecommerce.model.response.SanPhamResponse(ctsp.id,ctsp.tenSP,ctsp.Img,ctsp.giaBan,'') from ChiTietSanPham ctsp")
    List<SanPhamResponse> getAllSanPham();

    @Query("select new com.example.ecommerce.model.response.SanPhamResponse(ctsp.id,ctsp.tenSP,ctsp.Img,ctsp.giaBan,'') from ChiTietSanPham ctsp where ctsp.category.id = :id")
    List<SanPhamResponse> getAllSanPhamByCateID(@Param("id")String id);

    @Query("select new com.example.ecommerce.model.response.SanPhamResponse(ctsp.id,ctsp.tenSP,ctsp.Img,ctsp.giaBan,'') from ChiTietSanPham ctsp where ctsp.season.id = :id")
    List<SanPhamResponse> getAllSanPhamBySeasonID(@Param("id")String id);

    @Query("select new com.example.ecommerce.model.response.SanPhamResponse(ctsp.id,ctsp.tenSP,ctsp.Img,ctsp.giaBan,ctsp.moTa) from ChiTietSanPham ctsp where ctsp.id = :id")
    Optional<SanPhamResponse> getOneByID(@Param("id")String id);
}
