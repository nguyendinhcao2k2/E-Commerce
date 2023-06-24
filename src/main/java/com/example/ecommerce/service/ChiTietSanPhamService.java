package com.example.ecommerce.service;

import com.example.ecommerce.entity.ChiTietSanPham;
import com.example.ecommerce.model.request.ProductRequest;
import com.example.ecommerce.model.response.SanPhamResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    Integer countAllProduct();

    List<SanPhamResponse> getTop5();

    ChiTietSanPham saveProduct(ProductRequest productRequest, MultipartFile file) throws IOException;

    boolean updateProduct(String id,ProductRequest productRequest, MultipartFile file) throws IOException;

    boolean deleteProduct(String id) throws IOException;

}
