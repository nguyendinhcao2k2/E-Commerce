package com.example.ecommerce.service.impl;

import com.example.ecommerce.repository.GioHangRepository;
import com.example.ecommerce.service.GioHangChiTietService;
import com.example.ecommerce.service.GioHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author caodinh
 */
@Service
public class GioHangChiTietServiceImpl implements GioHangChiTietService {
    
    @Autowired
    private GioHangRepository gioHangRepository;
    
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
}
