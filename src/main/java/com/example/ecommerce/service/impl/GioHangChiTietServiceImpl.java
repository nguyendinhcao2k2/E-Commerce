package com.example.ecommerce.service.impl;

import com.example.ecommerce.model.response.GioHangChiTietResponse;
import com.example.ecommerce.repository.GioHangChiTietRepository;
import com.example.ecommerce.repository.GioHangRepository;
import com.example.ecommerce.service.GioHangChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author caodinh
 */
@Service
public class GioHangChiTietServiceImpl implements GioHangChiTietService {

    @Autowired
    private GioHangRepository gioHangRepository;

    @Autowired
    private GioHangChiTietRepository gioHangChiTietRepository;

    @Override
    public String getMaxMaChiTietGioHang() {
        String maCTGH = null;
        try {
            maCTGH = gioHangRepository.getMaxMaGioHang();
        } catch (Exception e) {
            // Xử lý ngoại lệ
        }

        if (maCTGH == null) {
            maCTGH = "1";
            int ma = Integer.valueOf(maCTGH);
            maCTGH = "CTGH00" + ma;
            return maCTGH;
        }

        int ma = Integer.valueOf(maCTGH);
        ma++;
        maCTGH = "CTGH00" + ma;
        return maCTGH;
    }

    @Override
    public List<GioHangChiTietResponse> getAllByIdGH(String id) {
        return gioHangChiTietRepository.getAllByIdGH(id);
    }
}
