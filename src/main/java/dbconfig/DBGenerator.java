package dbconfig;


import com.example.ecommerce.entity.Admin;
import com.example.ecommerce.entity.Category;
import com.example.ecommerce.entity.ChiTietSanPham;
import com.example.ecommerce.entity.ChucVu;
import com.example.ecommerce.entity.MauSac;
import com.example.ecommerce.entity.NhaSanXuat;
import com.example.ecommerce.entity.ProductColor;
import com.example.ecommerce.entity.ProductSize;
import com.example.ecommerce.entity.Season;
import com.example.ecommerce.entity.Size;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.infrastructures.constants.TypeSize;
import com.example.ecommerce.infrastructures.constants.TypeStatus;
import com.example.ecommerce.repository.AdminRepository;
import com.example.ecommerce.repository.CategoryRepository;
import com.example.ecommerce.repository.ChiTietSanPhamRepository;
import com.example.ecommerce.repository.ChucVuRepository;
import com.example.ecommerce.repository.MauSacRepository;
import com.example.ecommerce.repository.NhaSanXuatRepository;
import com.example.ecommerce.repository.ProductColorRepository;
import com.example.ecommerce.repository.ProductSizeRepository;
import com.example.ecommerce.repository.SeasonRepository;
import com.example.ecommerce.repository.SizeRepository;
import com.example.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.math.BigDecimal;


/**
 * @author caodinh
 */

@SpringBootApplication
@EnableJpaRepositories(
        basePackages = "com.example.ecommerce.repository"
)
public class DBGenerator implements CommandLineRunner {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductColorRepository productColorRepository;
    @Autowired
    private ChiTietSanPhamRepository chiTietSanPhamRepository;
    @Autowired
    private ChucVuRepository chucVuRepository;
    @Autowired
    private SeasonRepository seasonRepository;

    @Autowired
    private ProductSizeRepository productSizeRepository;
    @Autowired
    private SizeRepository sizeRepository;
    @Autowired
    private MauSacRepository mauSacRepository;
    @Autowired
    private NhaSanXuatRepository nhaSanXuatRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        //Gen Chức Vụ
        ChucVu chucVu = new ChucVu(null, "CV001", "User");
        chucVuRepository.save(chucVu);
        ChucVu chucVu1 = new ChucVu(null, "CV002", "Admin");
        chucVuRepository.save(chucVu1);

        //Gen Size
        Size sizeShoe35 = new Size(null, "SZ001", "35", TypeSize.SHOE_SIZE);
        sizeRepository.save(sizeShoe35);
        Size sizeShoe36 = new Size(null, "SZ002", "36", TypeSize.SHOE_SIZE);
        sizeRepository.save(sizeShoe36);
        Size sizeShoe37 = new Size(null, "SZ003", "37", TypeSize.SHOE_SIZE);
        sizeRepository.save(sizeShoe37);
        Size sizeShoe38 = new Size(null, "SZ004", "38", TypeSize.SHOE_SIZE);
        sizeRepository.save(sizeShoe38);
        Size sizeShoe39 = new Size(null, "SZ005", "39", TypeSize.SHOE_SIZE);
        sizeRepository.save(sizeShoe39);
        Size sizeShoe40 = new Size(null, "SZ007", "40", TypeSize.SHOE_SIZE);
        sizeRepository.save(sizeShoe40);
        Size sizeShoe41 = new Size(null, "SZ008", "41", TypeSize.SHOE_SIZE);
        sizeRepository.save(sizeShoe41);
        Size sizeShoe42 = new Size(null, "SZ009", "42", TypeSize.SHOE_SIZE);
        sizeRepository.save(sizeShoe42);
        Size sizeShoe43 = new Size(null, "SZ0010", "43", TypeSize.SHOE_SIZE);
        sizeRepository.save(sizeShoe43);
        Size sizeShoe44 = new Size(null, "SZ0011", "44", TypeSize.SHOE_SIZE);
        sizeRepository.save(sizeShoe44);

        Size sizeClothingS = new Size(null, "SZ0012", "S", TypeSize.CLOTHING_SIZE);
        sizeRepository.save(sizeClothingS);
        Size sizeClothingM = new Size(null, "SZ0013", "M", TypeSize.CLOTHING_SIZE);
        sizeRepository.save(sizeClothingM);
        Size sizeClothingL = new Size(null, "SZ0014", "L", TypeSize.CLOTHING_SIZE);
        sizeRepository.save(sizeClothingL);
        Size sizeClothingXL = new Size(null, "SZ0015", "XL", TypeSize.CLOTHING_SIZE);
        sizeRepository.save(sizeClothingXL);
        Size sizeClothingXXL = new Size(null, "SZ0016", "XXL", TypeSize.CLOTHING_SIZE);
        sizeRepository.save(sizeClothingXXL);

        //Gen Seson
        Season season = new Season(null, "SS001", "Summer", "/common/season_summer.png");
        seasonRepository.save(season);
        Season season2 = new Season(null, "SS002", "Autumn", "/common/season_autumn.png");
        seasonRepository.save(season2);
        Season season3 = new Season(null, "SS003", "Winter", "/common/season_winter.jpg");
        seasonRepository.save(season3);
        Season season4 = new Season(null, "SS004", "Spring", "/common/season_spring.jpg");
        seasonRepository.save(season4);

        //Gen Category
        Category category = new Category(null, "CT001", "Quần", "/common/quan_cate.webp");
        categoryRepository.save(category);
        Category category2 = new Category(null, "CT002", "Áo", "/common/ao_cate.webp");
        categoryRepository.save(category2);
        Category category3 = new Category(null, "CT003", "Giày", "/common/giay_cate.webp");
        categoryRepository.save(category3);

        //Gen Màu Sắc
        MauSac mauSac = new MauSac(null, "MS001", "Đỏ");
        mauSacRepository.save(mauSac);
        MauSac mauSac1 = new MauSac(null, "MS002", "Xanh");
        mauSacRepository.save(mauSac1);
        MauSac mauSac2 = new MauSac(null, "MS003", "Đen");
        mauSacRepository.save(mauSac2);
        MauSac mauSac3 = new MauSac(null, "MS004", "Trắng");
        mauSacRepository.save(mauSac3);
        MauSac mauSac4 = new MauSac(null, "MS005", "Vàng");
        mauSacRepository.save(mauSac4);

        //Gen NhaSan Xuat
        NhaSanXuat nhaSanXuat = new NhaSanXuat(null, "NSX001", "Premia");
        nhaSanXuatRepository.save(nhaSanXuat);


        //Gen ChiTiet San Pham
        ChiTietSanPham chiTietSanPham = new ChiTietSanPham(null, "Áo Polo Exmate Pro", nhaSanXuat, category2, season, "Chất Liệu Vải Được làm bằng Cotton mát mẻ không bị ngứa", "/ao/ao_polo.webp", 200, BigDecimal.valueOf(300), TypeStatus.AVAILABLE);
        chiTietSanPhamRepository.save(chiTietSanPham);
        ChiTietSanPham chiTietSanPham11 = new ChiTietSanPham(null, "Áo chống nắng Promier", nhaSanXuat, category2, season2, "Chất Liệu Vải Được làm bằng từ vải dù giúp người mặc cảm thấy không nóng cũng như không lạnh vào tiết trời dịu êm", "/ao/ao_unisex.webp", 200, BigDecimal.valueOf(400), TypeStatus.AVAILABLE);
        chiTietSanPhamRepository.save(chiTietSanPham11);
        ChiTietSanPham chiTietSanPham1 = new ChiTietSanPham(null, "Quần Jean Xanh Ngọc", nhaSanXuat, category, season4, "Chất Liệu Vải Được làm bằng diều gió mát mẻ không bị ngứa", "/quan/quan_jean.webp", 200, BigDecimal.valueOf(150), TypeStatus.AVAILABLE);
        chiTietSanPhamRepository.save(chiTietSanPham1);
        ChiTietSanPham chiTietSanPham12 = new ChiTietSanPham(null, "Quần đùi thoáng mát Exmate", nhaSanXuat, category, season, "Chất liệu được làm từ vải Cotton siêu thoáng mát thấm hút mồ hôi siêu đỉnh", "/quan/quan_vip_pro.webp", 200, BigDecimal.valueOf(150), TypeStatus.AVAILABLE);
        chiTietSanPhamRepository.save(chiTietSanPham12);
        ChiTietSanPham chiTietSanPham2 = new ChiTietSanPham(null, "Giày đẹp cho nam và nữ", nhaSanXuat, category3, season3, "Giày được làm bằng vải tơ tằm được dệt cách đây 300 năm,thuộc hàng cao cấp và hiếm nhất", "/giay/giay_cho_nam_nu.jpg", 200, BigDecimal.valueOf(400), TypeStatus.AVAILABLE);
        chiTietSanPhamRepository.save(chiTietSanPham2);
        ChiTietSanPham chiTietSanPham22 = new ChiTietSanPham(null, "Giày đẹp cho nam Exmate Cool", nhaSanXuat, category3, season4, "Giày được làm bằng vải lụa thời coogggo từ 2000 năm trước", "/giay/giay_cho_nam.webp", 200, BigDecimal.valueOf(900), TypeStatus.AVAILABLE);
        chiTietSanPhamRepository.save(chiTietSanPham22);

        //Gen ProductColor
        ProductColor productColor1 = new ProductColor(null, chiTietSanPham, mauSac);
        productColorRepository.save(productColor1);
        ProductColor productColor2 = new ProductColor(null, chiTietSanPham, mauSac1);
        productColorRepository.save(productColor2);
        ProductColor productColor3 = new ProductColor(null, chiTietSanPham, mauSac2);
        productColorRepository.save(productColor3);
        ProductColor productColor4 = new ProductColor(null, chiTietSanPham, mauSac3);
        productColorRepository.save(productColor4);
        ProductColor productColor5 = new ProductColor(null,chiTietSanPham,mauSac4);
        productColorRepository.save(productColor5);

        ProductColor productColor6 = new ProductColor(null, chiTietSanPham11, mauSac);
        productColorRepository.save(productColor6);
        ProductColor productColor7 = new ProductColor(null, chiTietSanPham11, mauSac1);
        productColorRepository.save(productColor7);
        ProductColor productColor8 = new ProductColor(null, chiTietSanPham11, mauSac2);
        productColorRepository.save(productColor8);
        ProductColor productColor9 = new ProductColor(null, chiTietSanPham11, mauSac3);
        productColorRepository.save(productColor9);
        ProductColor productColor10 = new ProductColor(null,chiTietSanPham11,mauSac4);
        productColorRepository.save(productColor10);

        ProductColor productColor11 = new ProductColor(null, chiTietSanPham1, mauSac);
        productColorRepository.save(productColor11);
        ProductColor productColor12 = new ProductColor(null, chiTietSanPham1, mauSac1);
        productColorRepository.save(productColor12);
        ProductColor productColor13 = new ProductColor(null, chiTietSanPham1, mauSac2);
        productColorRepository.save(productColor13);
        ProductColor productColor14 = new ProductColor(null, chiTietSanPham1, mauSac3);
        productColorRepository.save(productColor14);
        ProductColor productColor15 = new ProductColor(null,chiTietSanPham1,mauSac4);
        productColorRepository.save(productColor15);

        ProductColor productColor16 = new ProductColor(null, chiTietSanPham12, mauSac);
        productColorRepository.save(productColor16);
        ProductColor productColor17 = new ProductColor(null, chiTietSanPham12, mauSac1);
        productColorRepository.save(productColor17);
        ProductColor productColor18 = new ProductColor(null, chiTietSanPham12, mauSac2);
        productColorRepository.save(productColor18);
        ProductColor productColor19 = new ProductColor(null, chiTietSanPham12, mauSac3);
        productColorRepository.save(productColor19);
        ProductColor productColor20 = new ProductColor(null,chiTietSanPham12,mauSac4);
        productColorRepository.save(productColor20);

        ProductColor productColor21 = new ProductColor(null, chiTietSanPham2, mauSac);
        productColorRepository.save(productColor21);
        ProductColor productColor22 = new ProductColor(null, chiTietSanPham2, mauSac1);
        productColorRepository.save(productColor22);
        ProductColor productColor23 = new ProductColor(null, chiTietSanPham2, mauSac2);
        productColorRepository.save(productColor23);
        ProductColor productColor24 = new ProductColor(null, chiTietSanPham2, mauSac3);
        productColorRepository.save(productColor24);
        ProductColor productColor25 = new ProductColor(null,chiTietSanPham2,mauSac4);
        productColorRepository.save(productColor25);

        ProductColor productColor26 = new ProductColor(null, chiTietSanPham22, mauSac);
        productColorRepository.save(productColor26);
        ProductColor productColor27 = new ProductColor(null, chiTietSanPham22, mauSac1);
        productColorRepository.save(productColor27);
        ProductColor productColor28 = new ProductColor(null, chiTietSanPham22, mauSac2);
        productColorRepository.save(productColor28);
        ProductColor productColor29 = new ProductColor(null, chiTietSanPham22, mauSac3);
        productColorRepository.save(productColor29);
        ProductColor productColor30 = new ProductColor(null,chiTietSanPham22,mauSac4);
        productColorRepository.save(productColor30);

        //Gen ProductSize
        ProductSize productSize1 = new ProductSize(null, chiTietSanPham, sizeClothingS);
        productSizeRepository.save(productSize1);
        ProductSize productSize2 = new ProductSize(null, chiTietSanPham, sizeClothingM);
        productSizeRepository.save(productSize2);
        ProductSize productSize3 = new ProductSize(null, chiTietSanPham, sizeClothingL);
        productSizeRepository.save(productSize3);
        ProductSize productSize4 = new ProductSize(null, chiTietSanPham, sizeClothingXL);
        productSizeRepository.save(productSize4);
        ProductSize productSize5 = new ProductSize(null, chiTietSanPham, sizeClothingXXL);
        productSizeRepository.save(productSize5);

        ProductSize productSize6 = new ProductSize(null, chiTietSanPham11, sizeClothingS);
        productSizeRepository.save(productSize6);
        ProductSize productSize7 = new ProductSize(null, chiTietSanPham11, sizeClothingM);
        productSizeRepository.save(productSize7);
        ProductSize productSize8 = new ProductSize(null, chiTietSanPham11, sizeClothingL);
        productSizeRepository.save(productSize8);
        ProductSize productSize9 = new ProductSize(null, chiTietSanPham11, sizeClothingXL);
        productSizeRepository.save(productSize9);
        ProductSize productSize10 = new ProductSize(null, chiTietSanPham11, sizeClothingXXL);
        productSizeRepository.save(productSize10);

        ProductSize productSize11 = new ProductSize(null, chiTietSanPham1, sizeClothingS);
        productSizeRepository.save(productSize11);
        ProductSize productSize12 = new ProductSize(null, chiTietSanPham1, sizeClothingM);
        productSizeRepository.save(productSize12);
        ProductSize productSize13 = new ProductSize(null, chiTietSanPham1, sizeClothingL);
        productSizeRepository.save(productSize13);
        ProductSize productSize14 = new ProductSize(null, chiTietSanPham1, sizeClothingXL);
        productSizeRepository.save(productSize14);
        ProductSize productSize15 = new ProductSize(null, chiTietSanPham1, sizeClothingXXL);
        productSizeRepository.save(productSize15);

        ProductSize productSize16 = new ProductSize(null, chiTietSanPham12, sizeClothingS);
        productSizeRepository.save(productSize16);
        ProductSize productSize17 = new ProductSize(null, chiTietSanPham12, sizeClothingM);
        productSizeRepository.save(productSize17);
        ProductSize productSize18 = new ProductSize(null, chiTietSanPham12, sizeClothingL);
        productSizeRepository.save(productSize18);
        ProductSize productSize19 = new ProductSize(null, chiTietSanPham12, sizeClothingXL);
        productSizeRepository.save(productSize19);
        ProductSize productSize20 = new ProductSize(null, chiTietSanPham12, sizeClothingXXL);
        productSizeRepository.save(productSize20);

        ProductSize productSize21 = new ProductSize(null, chiTietSanPham2, sizeShoe38);
        productSizeRepository.save(productSize21);
        ProductSize productSize22 = new ProductSize(null, chiTietSanPham2, sizeShoe39);
        productSizeRepository.save(productSize22);
        ProductSize productSize23 = new ProductSize(null, chiTietSanPham2, sizeShoe40);
        productSizeRepository.save(productSize23);
        ProductSize productSize24 = new ProductSize(null, chiTietSanPham2, sizeShoe41);
        productSizeRepository.save(productSize24);
        ProductSize productSize25 = new ProductSize(null, chiTietSanPham2, sizeShoe42);
        productSizeRepository.save(productSize25);

        ProductSize productSize26 = new ProductSize(null, chiTietSanPham22, sizeShoe38);
        productSizeRepository.save(productSize26);
        ProductSize productSize27 = new ProductSize(null, chiTietSanPham22, sizeShoe39);
        productSizeRepository.save(productSize27);
        ProductSize productSize28 = new ProductSize(null, chiTietSanPham22, sizeShoe40);
        productSizeRepository.save(productSize28);
        ProductSize productSize29 = new ProductSize(null, chiTietSanPham22, sizeShoe41);
        productSizeRepository.save(productSize29);
        ProductSize productSize30 = new ProductSize(null, chiTietSanPham22, sizeShoe42);
        productSizeRepository.save(productSize30);


        //Gen Admin
        Admin admin = new Admin(null, "AM001", "Nam Dinh", "0943232913", "namdinh", "123456", chucVu1);
        adminRepository.save(admin);
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(DBGenerator.class);
        ctx.close();
    }

}