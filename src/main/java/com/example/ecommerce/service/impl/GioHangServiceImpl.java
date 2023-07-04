package com.example.ecommerce.service.impl;

import com.example.ecommerce.entity.GioHang;
import com.example.ecommerce.entity.HoaDon;
import com.example.ecommerce.infrastructures.constants.TypeHoaDon;
import com.example.ecommerce.model.response.CartInfoResponse;
import com.example.ecommerce.model.response.HoaDonResponse;
import com.example.ecommerce.repository.GioHangRepository;
import com.example.ecommerce.repository.HoaDonRepository;
import com.example.ecommerce.service.GioHangService;
import com.example.ecommerce.service.HoaDonService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    @Override
    public List<HoaDonResponse> getHoaDonByTrangThai() {
        return gioHangRepository.getHoaDonByTrangThai();
    }

    @Override
    public boolean updateHoaDon(String id) {
        Optional<GioHang> optionalGioHang = gioHangRepository.findById(id);
        if (optionalGioHang.isPresent()) {
            GioHang gioHang = optionalGioHang.get();
            gioHang.getHoaDon().setTinhTrang(TypeHoaDon.CANCELLED.toString());
            gioHangRepository.save(gioHang);

            return true;
        }
        return false;
    }

    @Override
    public boolean confirmHoaDon(String id) {
        Optional<GioHang> optionalGioHang = gioHangRepository.findById(id);
        if (optionalGioHang.isPresent()) {
            GioHang gioHang = optionalGioHang.get();
            gioHang.getHoaDon().setTinhTrang(TypeHoaDon.SUCCESS.toString());
            gioHang.getHoaDon().setNgayNhan(new Date());
            gioHang.getHoaDon().setNgayThanhToan(new Date());
            gioHangRepository.save(gioHang);

            return true;
        }
        return false;
    }

    @Override
    public boolean switchState(String id) {
        Optional<GioHang> optionalGioHang = gioHangRepository.findById(id);
        if (optionalGioHang.isPresent()) {
            GioHang gioHang = optionalGioHang.get();
            gioHang.getHoaDon().setTinhTrang(TypeHoaDon.SHIPPING.toString());
            gioHang.getHoaDon().setNgayShip(new Date());
            gioHangRepository.save(gioHang);
            return true;
        }
        return false;
    }


}
