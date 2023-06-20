package com.example.ecommerce.controller;

import com.example.ecommerce.entity.ChiTietSanPham;
import com.example.ecommerce.entity.GioHang;
import com.example.ecommerce.entity.GioHangChiTiet;
import com.example.ecommerce.model.response.CartInfoResponse;
import com.example.ecommerce.repository.ChiTietSanPhamRepository;
import com.example.ecommerce.service.GioHangService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author caodinh
 */
@RestController
@RequestMapping("/api/user")
public class CartRestController {

    @Autowired
    private ChiTietSanPhamRepository chiTietSanPhamRepository;
    @Autowired
    private GioHangService gioHangService;

    @PostMapping("/add-to-cart")
    public ResponseEntity<String> addToCart(HttpSession session,
                                            @RequestParam("color") String color,
                                            @RequestParam("size") String size,
                                            @RequestParam("amount") int amount,
                                            @RequestParam("id") String id) {
        addCartToSession(session, color, size, amount, id);
        return ResponseEntity.ok("Add To Cart Success");
    }

    @PutMapping("/update-amount-cart")
    public ResponseEntity<?> minuseAmountCart(HttpSession session,
                                              @RequestParam("color") String color,
                                              @RequestParam("size") String size,
                                              @RequestParam("amount") int amount,
                                              @RequestParam("id") String id) {
        GioHang gioHang = (GioHang) session.getAttribute("carts");
        if (gioHang == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Update Fail");
        }
        for (int i = 0; i < gioHang.getChiTietGioHang().size(); i++) {
            if (gioHang.getChiTietGioHang().get(i).getChiTietSP().getId().equals(id) && gioHang.getChiTietGioHang().get(i).getSizeName().equals(size)
                    && gioHang.getChiTietGioHang().get(i).getColor_name().equals(color)) {
                gioHang.getChiTietGioHang().get(i).setSoLuong(amount);
            }
        }
        // Tiếp tục xử lý nếu không có lỗi
        return new ResponseEntity(gioHang.getChiTietGioHang(), HttpStatus.OK);
    }

    @DeleteMapping("/delete-cart")
    public ResponseEntity<String> deleteToCart(HttpSession session,
                                               @RequestParam("color") String color,
                                               @RequestParam("size") String size,
                                               @RequestParam("id") String id) {
        GioHang gioHang = (GioHang) session.getAttribute("carts");
        if (gioHang == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Delete Fail");
        }
        for (int i = 0; i < gioHang.getChiTietGioHang().size(); i++) {
            if (gioHang.getChiTietGioHang().get(i).getChiTietSP().getId().equals(id) && gioHang.getChiTietGioHang().get(i).getSizeName().equals(size)
                    && gioHang.getChiTietGioHang().get(i).getColor_name().equals(color)) {
                gioHang.getChiTietGioHang().remove(i);
            }
        }
        // Tiếp tục xử lý nếu không có lỗi
        return ResponseEntity.ok("Delete Cart Success");
    }

    public void addCartToSession(HttpSession session, String color, String size, int amount, String id) {
        GioHang gioHang = (GioHang) session.getAttribute("carts");
        Optional<ChiTietSanPham> chiTietSanPham = chiTietSanPhamRepository.findById(id);
        if (gioHang == null) {
            gioHang = new GioHang();
            gioHang.setNgayTao(new Date());
            gioHang.setMa(gioHangService.getMaxMaGioHang());
            List<GioHangChiTiet> gioHangChiTietList = new ArrayList<>();
            //Thêm Giỏ hàng chi tiết vào giỏ hàng
            GioHangChiTiet gioHangChiTiet = new GioHangChiTiet();
            gioHangChiTiet.setChiTietSP(chiTietSanPham.get());
            gioHangChiTiet.setGioHang(gioHang);
            gioHangChiTiet.setColor_name(color);
            gioHangChiTiet.setSizeName(size);
            gioHangChiTiet.setSoLuong(amount);
            gioHangChiTiet.setDonGia(chiTietSanPham.get().getGiaBan());
            gioHangChiTietList.add(gioHangChiTiet);
            gioHang.setChiTietGioHang(gioHangChiTietList);
            session.setAttribute("carts", gioHang);
        } else {
            gioHang.setMa(gioHangService.getMaxMaGioHang());
            List<GioHangChiTiet> gioHangChiTietList = gioHang.getChiTietGioHang();
            //Kiểm Tra Xem sản phẩm đã có trong giỏ hàng hay chưa
            boolean check = false;
            if (gioHangChiTietList.size() > 0) {
                for (int i = 0; i < gioHangChiTietList.size(); i++) {
                    if (gioHangChiTietList.get(i).getChiTietSP().getId().equals(chiTietSanPham.get().getId())
                            && gioHangChiTietList.get(i).getColor_name().equals(color)
                            && gioHangChiTietList.get(i).getSizeName().equals(size)) {
                        gioHangChiTietList.get(i).setSoLuong(gioHangChiTietList.get(i).getSoLuong() + 1);
                        check = true;
                    }
                }
            }
            //Kiểm tra nếu không có sản phẩm nào trong giỏ hàng sẽ vào if còn lại sẽ vào else
            if (check == false) {
                GioHangChiTiet gioHangChiTiet = new GioHangChiTiet();
                gioHangChiTiet.setChiTietSP(chiTietSanPham.get());
                gioHangChiTiet.setGioHang(gioHang);
                gioHangChiTiet.setColor_name(color);
                gioHangChiTiet.setSizeName(size);
                gioHangChiTiet.setSoLuong(amount);
                gioHangChiTiet.setDonGia(chiTietSanPham.get().getGiaBan());

                gioHangChiTietList.add(gioHangChiTiet);
                //set lại data cho giỏ hàng rồi trả về trang chủ
                session.setAttribute("carts", gioHang);
            } else {
                session.setAttribute("carts", gioHang);
            }
        }
    }

    @PostMapping("/check-out-cart")
    public ResponseEntity<String> saveCart(@RequestBody CartInfoResponse cartInfoResponse, HttpSession session) {
        GioHang gioHang = (GioHang) session.getAttribute("carts");
        if (gioHang != null) {
            System.out.println(cartInfoResponse.toString());
            gioHangService.addToCart(gioHang, cartInfoResponse);
            GioHang newGH = new GioHang();
            newGH.setChiTietGioHang(new ArrayList<>());
            session.setAttribute("carts", newGH);
            return new ResponseEntity<>("Check out Success", HttpStatus.OK);
        } else {
            System.err.println("error");
            return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
        }
    }
}
