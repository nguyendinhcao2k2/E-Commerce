package com.example.ecommerce.service.impl;

import com.example.ecommerce.entity.ChiTietSanPham;
import com.example.ecommerce.entity.ProductSize;
import com.example.ecommerce.entity.Size;
import com.example.ecommerce.model.response.ProductSizeResponse;
import com.example.ecommerce.repository.ChiTietSanPhamRepository;
import com.example.ecommerce.repository.ProductSizeRepository;
import com.example.ecommerce.repository.SizeRepository;
import com.example.ecommerce.service.ProductSizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author caodinh
 */
@Service
public class ProductSizeServiceImpl implements ProductSizeService {

    @Autowired
    private ProductSizeRepository productSizeRepository;

    @Autowired
    private SizeRepository sizeRepository;

    @Autowired
    private ChiTietSanPhamRepository chiTietSanPhamRepository;

    @Override
    public List<ProductSizeResponse> getAllByCtspID(String id) {
        return productSizeRepository.getAllByCtspID(id);
    }

    @Override
    public boolean save(String proId, List<String> shoeSize, List<String> clothongSize) {
        List<ProductSize> productSizes = new ArrayList<>();
        Optional<ChiTietSanPham> chiTietSanPham = chiTietSanPhamRepository.findById(proId);
        if (chiTietSanPham.isPresent()) {
            if (shoeSize.size() > 0) {
                List<Size> sizes = sizeRepository.findAllById(shoeSize);
                for (Size s : sizes) {
                    productSizes.add(new ProductSize(null, chiTietSanPham.get(), s));
                }
            } else {
                List<Size> sizes = sizeRepository.findAllById(clothongSize);
                for (Size s : sizes) {
                    productSizes.add(new ProductSize(null, chiTietSanPham.get(), s));
                }
            }
            productSizeRepository.saveAll(productSizes);
            return true;
        }
        return false;
    }
}
