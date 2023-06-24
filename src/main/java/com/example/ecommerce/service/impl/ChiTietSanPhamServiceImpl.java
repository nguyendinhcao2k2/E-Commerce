package com.example.ecommerce.service.impl;

import com.example.ecommerce.entity.Category;
import com.example.ecommerce.entity.ChiTietSanPham;
import com.example.ecommerce.entity.Season;
import com.example.ecommerce.infrastructures.constants.TypeStatus;
import com.example.ecommerce.model.request.ProductRequest;
import com.example.ecommerce.model.response.SanPhamResponse;
import com.example.ecommerce.repository.CategoryRepository;
import com.example.ecommerce.repository.ChiTietSanPhamRepository;
import com.example.ecommerce.repository.ProductColorRepository;
import com.example.ecommerce.repository.ProductSizeRepository;
import com.example.ecommerce.repository.SeasonRepository;
import com.example.ecommerce.service.ChiTietSanPhamService;
import com.example.ecommerce.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author caodinh
 */
@Service
public class ChiTietSanPhamServiceImpl implements ChiTietSanPhamService {

    @Autowired
    private ChiTietSanPhamRepository chiTietSanPhamRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SeasonRepository seasonRepository;

    @Autowired
    private ProductSizeRepository productSizeRepository;

    @Autowired
    private ProductColorRepository productColorRepository;

    @Override
    public List<SanPhamResponse> getAllSanPham() {
        return chiTietSanPhamRepository.getAllSanPham();
    }

    @Override
    public List<SanPhamResponse> getAllSanPhamByCateId(String id) {
        return chiTietSanPhamRepository.getAllSanPhamByCateID(id);
    }

    @Override
    public List<SanPhamResponse> getAllSanPhamBySeasonId(String id) {
        return chiTietSanPhamRepository.getAllSanPhamBySeasonID(id);
    }

    @Override
    public Optional<SanPhamResponse> getOneByID(String id) {
        Optional<SanPhamResponse> sanPhamResponse = chiTietSanPhamRepository.getOneByID(id);
        if (!sanPhamResponse.isPresent()) {
            throw new RuntimeException("Product not found");
        }
        return sanPhamResponse;
    }

    @Override
    public List<SanPhamResponse> searchByName(String tenSP) {
        return chiTietSanPhamRepository.searchByName(tenSP);
    }

    @Override
    public Integer countAllProduct() {
        return chiTietSanPhamRepository.countAllProduct();
    }

    @Override
    public List<SanPhamResponse> getTop5() {
        Pageable pageable = PageRequest.of(0, 5);
        return chiTietSanPhamRepository.getTop5(pageable);
    }

    @Override
    public ChiTietSanPham saveProduct(ProductRequest productRequest, MultipartFile file) throws IOException {
        Optional<Category> category = categoryRepository.findById(productRequest.getCategory());
        Optional<Season> season = seasonRepository.findById(productRequest.getSeason());

        if (!category.isPresent() || !season.isPresent()) {
            return null;
        }
        String path = "";
        String savePath = "";
        if (category.get().getTen().equals("Giày")) {
            path = "static/img/giay/";
            savePath = "/giay/";
        } else if (category.get().getTen().equals("Áo")) {
            path = "static/img/ao/";
            savePath = "/ao/";
        } else if (category.get().getTen().equals("Quần")) {
            path = "static/img/quan/";
            savePath = "/quan/";
        }

        String image = ImageUtil.saveImage(file, path);
        if (image == null) {
            return null;
        }

        ChiTietSanPham chiTietSanPham = new ChiTietSanPham(null, productRequest.getTenSP(), null, category.get(), season.get(), productRequest.getDescription(), savePath + image, productRequest.getAmount(), BigDecimal.valueOf(productRequest.getPrice()), new Date(), TypeStatus.AVAILABLE);
        return chiTietSanPhamRepository.save(chiTietSanPham);
    }

    @Override
    public boolean updateProduct(String id, ProductRequest productRequest, MultipartFile file) throws IOException {
        Optional<ChiTietSanPham> chiTietSanPhamOptional = chiTietSanPhamRepository.findById(id);
        if (!chiTietSanPhamOptional.isPresent()) {
            return false;
        }

        if (ImageUtil.deleteImage(chiTietSanPhamOptional.get().getImg())) {
            Optional<Category> category = categoryRepository.findById(productRequest.getCategory());
            Optional<Season> season = seasonRepository.findById(productRequest.getSeason());

            if (!category.isPresent() || !season.isPresent()) {
                return false;
            }
            String path = "";
            String savePath = "";
            if (category.get().getTen().equals("Giày")) {
                path = "static/img/giay/";
                savePath = "/giay/";
            } else if (category.get().getTen().equals("Áo")) {
                path = "static/img/ao/";
                savePath = "/ao/";
            } else if (category.get().getTen().equals("Quần")) {
                path = "static/img/quan/";
                savePath = "/quan/";
            }

            String image = ImageUtil.saveImage(file, path);
            if (image == null) {
                return false;
            }

            ChiTietSanPham chiTietSanPham = chiTietSanPhamOptional.get();
            chiTietSanPham.setTenSP(productRequest.getTenSP());
            chiTietSanPham.setSeason(season.get());
            chiTietSanPham.setCategory(category.get());
            chiTietSanPham.setMoTa(productRequest.getDescription());
            chiTietSanPham.setImg(savePath + image);
            chiTietSanPham.setSoLuongTon(productRequest.getAmount());
            chiTietSanPham.setGiaBan(BigDecimal.valueOf(productRequest.getPrice()));
            if (chiTietSanPhamRepository.save(chiTietSanPham) == null) {
                return false;
            }
            return true;
        }

        return false;
    }

    @Override
    @Transactional
    public boolean deleteProduct(String id) throws IOException {
        Optional<ChiTietSanPham> chiTietSanPham = chiTietSanPhamRepository.findById(id);
        if (!chiTietSanPham.isPresent()) {
            return false;
        }
        if (ImageUtil.deleteImage(chiTietSanPham.get().getImg())) {
            productSizeRepository.deleteByCtspId(id);
            productColorRepository.deleteByProductId(id);
            chiTietSanPhamRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
