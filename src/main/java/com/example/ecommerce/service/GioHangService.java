package com.example.ecommerce.service;

import com.example.ecommerce.entity.GioHang;
import com.example.ecommerce.model.response.CartInfoResponse;
import com.example.ecommerce.model.response.HoaDonResponse;

import java.util.List;

/**
 * @author caodinh
 */
public interface GioHangService {

    String getMaxMaGioHang();

    void addToCart(GioHang gioHang, CartInfoResponse cartInfoResponse);

    List<HoaDonResponse> getHoaDonByTrangThai();

}
