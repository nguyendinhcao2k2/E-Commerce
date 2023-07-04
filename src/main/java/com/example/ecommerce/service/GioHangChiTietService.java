package com.example.ecommerce.service;

import com.example.ecommerce.model.response.GioHangChiTietResponse;

import java.util.List;

/**
 * @author caodinh
 */
public interface GioHangChiTietService {

    String getMaxMaChiTietGioHang();

    List<GioHangChiTietResponse> getAllByIdGH(String id);

}
