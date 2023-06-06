package com.example.ecommerce.service.impl;

import com.example.ecommerce.entity.GioHang;
import com.example.ecommerce.entity.HoaDon;
import com.example.ecommerce.infrastructures.constants.TypeHoaDon;
import com.example.ecommerce.repository.HoaDonRepository;
import com.example.ecommerce.service.GioHangService;
import com.example.ecommerce.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author caodinh
 */
@Service
public class HoaDonServiceImpl implements HoaDonService {

    @Autowired
    private HoaDonRepository hoaDonRepository;


    @Override
    public String getMaxMaHoaDon() {
        String maHD = null;
        try {
            maHD = hoaDonRepository.getMaxMaHoaDon();
        } catch (Exception e) {
            // Xử lý ngoại lệ
        }

        if (maHD == null) {
            maHD = "1";
            int ma = Integer.valueOf(maHD);
            maHD = "HD00" + ma;
            return maHD;
        }

        int ma = Integer.valueOf(maHD);
        ma++;
        maHD = "GH00" + ma;
        return maHD;
    }

}
