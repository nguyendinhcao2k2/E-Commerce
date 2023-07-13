package com.example.ecommerce.service.impl;

import com.example.ecommerce.model.response.MonthAmountResponse;
import com.example.ecommerce.model.response.ThuAmountResponse;
import com.example.ecommerce.repository.HoaDonRepository;
import com.example.ecommerce.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

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
        maHD = "HD00" + ma;
        return maHD;
    }

    @Override
    public BigDecimal getTotalPrice() {
        return hoaDonRepository.getTotalPrice();
    }

    @Override
    public int totalInvoice() {
        return hoaDonRepository.totalInvoice();
    }

    @Override
    public int cancelInvoice() {
        return hoaDonRepository.cancelInvoice();
    }

    @Override
    public List<ThuAmountResponse> getThuAndAmount() {
        return hoaDonRepository.getThuAndAmount();
    }

    @Override
    public List<MonthAmountResponse> getAmountByMonth() {
        return hoaDonRepository.getAmountByMonth();
    }

    @Override
    public List<ThuAmountResponse> getAmountByYear() {
        return hoaDonRepository.getAmountByYear();
    }


}
