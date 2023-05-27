package com.example.ecommerce.service.impl;

import com.example.ecommerce.model.response.SanPhamResponse;
import com.example.ecommerce.repository.ChiTietSanPhamRepository;
import com.example.ecommerce.service.ChiTietSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author caodinh
 */
@Service
public class ChiTietSanPhamServiceImpl implements ChiTietSanPhamService {

    @Autowired
    private ChiTietSanPhamRepository chiTietSanPhamRepository;

    @Override
    public List<SanPhamResponse> getAllSanPham() {
        return chiTietSanPhamRepository.getAllSanPham();
    }

    @Override
    public List<SanPhamResponse> getAllSanPhamByCateId(String id) {
        return chiTietSanPhamRepository.getAllSanPhamByCateID(id);
    }

    @Override
    public List<SanPhamResponse> getAllSanPhamBySeasonId(String id) {
        return chiTietSanPhamRepository.getAllSanPhamBySeasonID(id);
    }

    @Override
    public Optional<SanPhamResponse> getOneByID(String id) {
        Optional<SanPhamResponse> sanPhamResponse = chiTietSanPhamRepository.getOneByID(id);
        if(!sanPhamResponse.isPresent()){
            throw new RuntimeException("Product not found");
        }
        return sanPhamResponse;
    }
}
