package com.example.ecommerce.controller.admin;

import com.example.ecommerce.model.request.ProductRequest;
import com.example.ecommerce.service.ChiTietSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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


    @PostMapping("/save-product")
    public ResponseEntity<String> saveProduct(@ModelAttribute ProductRequest productRequest, @RequestParam("image") MultipartFile image) throws IOException {
        if (chiTietSanPhamService.saveProduct(productRequest, image)) {
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

}