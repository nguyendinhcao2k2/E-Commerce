package com.example.ecommerce.service.impl;

import com.example.ecommerce.entity.GioHang;
import com.example.ecommerce.entity.HoaDon;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.infrastructures.constants.TypeHoaDon;
import com.example.ecommerce.model.response.CartInfoResponse;
import com.example.ecommerce.repository.GioHangRepository;
import com.example.ecommerce.repository.HoaDonRepository;
import com.example.ecommerce.service.GioHangService;
import com.example.ecommerce.service.HoaDonService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author caodinh
 */
@Service
public class GioHangServiceImpl implements GioHangService {

    @Autowired
    private GioHangRepository gioHangRepository;

    @Autowired
    private HoaDonService hoaDonService;

    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Autowired
    private HttpSession httpSession;

    @Override
    public String getMaxMaGioHang() {
        String maGH = null;
        try {
            maGH = gioHangRepository.getMaxMaGioHang();
        } catch (Exception e) {
            // Xử lý ngoại lệ
        }

        if (maGH == null) {
            maGH = "1";
            int ma = Integer.valueOf(maGH);
            maGH = "GH00" + ma;
            return maGH;
        }

        int ma = Integer.valueOf(maGH);
        ma++;
        maGH = "GH00" + ma;
        return maGH;
    }

    @Override
    public void addToCart(GioHang gioHang, CartInfoResponse cartInfoResponse) {
        gioHang.setNgayTao(new Date());
        gioHang.setDiaChi(cartInfoResponse.getAddress());
        gioHang.setTenNguoiNhan(cartInfoResponse.getFirstName() + " " + cartInfoResponse.getLastName());
        gioHang.setSdt(cartInfoResponse.getPhoneNumber());
        gioHang.setEmail(cartInfoResponse.getEmail());
        gioHang.setNote(cartInfoResponse.getNote());

        HoaDon hoaDon = new HoaDon();
        hoaDon.setMa(hoaDonService.getMaxMaHoaDon());
        hoaDon.setTinhTrang(TypeHoaDon.PROCESSING.toString());
        hoaDon.setNgayTao(new Date());
        gioHang.setHoaDon(hoaDonRepository.save(hoaDon));

        gioHangRepository.save(gioHang);
    }


}
