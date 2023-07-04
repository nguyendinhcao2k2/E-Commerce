package com.example.ecommerce.controller.admin;

import com.example.ecommerce.entity.ChiTietSanPham;
import com.example.ecommerce.model.request.ProductRequest;
import com.example.ecommerce.service.ChiTietSanPhamService;
import com.example.ecommerce.service.GioHangChiTietService;
import com.example.ecommerce.service.GioHangService;
import com.example.ecommerce.service.ProductColorService;
import com.example.ecommerce.service.ProductSizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author caodinh
 */
@RestController
@RequestMapping("/api/admin")
public class AdminRescontroller {

    @Autowired
    private ChiTietSanPhamService chiTietSanPhamService;

    @Autowired
    private ProductSizeService productSizeService;

    @Autowired
    private ProductColorService productColorService;

    @Autowired
    private GioHangService gioHangService;

    @Autowired
    private GioHangChiTietService gioHangChiTietService;

    @PostMapping("/save-product")
    public ResponseEntity<String> saveProduct(@ModelAttribute ProductRequest productRequest, @RequestParam("image") MultipartFile image) throws IOException {
        ChiTietSanPham chiTietSanPham = chiTietSanPhamService.saveProduct(productRequest, image);
        if (chiTietSanPham != null) {
            productSizeService.save(chiTietSanPham.getId(), productRequest.getShoeSize(), productRequest.getClothingSize());
            productColorService.save(chiTietSanPham.getId(), productRequest.getColors());
            return ResponseEntity.ok("Save Data Success");
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/update-product/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable("id") String id, @ModelAttribute ProductRequest productRequest, @RequestParam("image") MultipartFile image) throws IOException {
        if (chiTietSanPhamService.updateProduct(id, productRequest, image)) {
            return ResponseEntity.ok("Update Data Success");
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/delete-product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") String id) throws IOException {
        if (chiTietSanPhamService.deleteProduct(id)) {
            return ResponseEntity.ok("Delete successfully !!");
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/cancel/{id}")
    public ResponseEntity<String> cancelHoaDon(@PathVariable("id") String id) {
        if (gioHangService.updateHoaDon(id)) {
            return ResponseEntity.ok("Cancel successfully !!");
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/confirm/{id}")
    public ResponseEntity<String> confirmHoaDon(@PathVariable("id") String id) {
        if (gioHangService.confirmHoaDon(id)) {
            return ResponseEntity.ok("Confirm successfully !!");
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/shipping/{id}")
    public ResponseEntity<String> shippingmHoaDon(@PathVariable("id") String id) {
        if (gioHangService.switchState(id)) {
            return ResponseEntity.ok("Switch state successfully !!");
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/detail-hoa-don/{id}")
    public ResponseEntity<?> detailHoaDon(@PathVariable("id") String id) {
        return new ResponseEntity<>(gioHangChiTietService.getAllByIdGH(id), HttpStatus.OK);
    }

}
