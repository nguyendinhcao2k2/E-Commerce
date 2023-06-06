package com.example.ecommerce.service;

import com.example.ecommerce.entity.GioHang;
import com.example.ecommerce.model.response.CartInfoResponse;

/**
 * @author caodinh
 */
public interface GioHangService {

    String getMaxMaGioHang();

    void addToCart(GioHang gioHang, CartInfoResponse cartInfoResponse);

}
