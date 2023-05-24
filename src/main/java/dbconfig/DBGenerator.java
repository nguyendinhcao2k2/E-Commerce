package dbconfig;


import com.example.ecommerce.entity.Admin;
import com.example.ecommerce.entity.Category;
import com.example.ecommerce.entity.ChiTietSanPham;
import com.example.ecommerce.entity.ChucVu;
import com.example.ecommerce.entity.MauSac;
import com.example.ecommerce.entity.NhaSanXuat;
import com.example.ecommerce.entity.Season;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.repository.AdminRepository;
import com.example.ecommerce.repository.CategoryRepository;
import com.example.ecommerce.repository.ChiTietSanPhamRepository;
import com.example.ecommerce.repository.ChucVuRepository;
import com.example.ecommerce.repository.GioHangChiTietRepository;
import com.example.ecommerce.repository.GioHangRepository;
import com.example.ecommerce.repository.HoaDonRepository;
import com.example.ecommerce.repository.MauSacRepository;
import com.example.ecommerce.repository.NhaSanXuatRepository;
import com.example.ecommerce.repository.SeasonRepository;
import com.example.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.math.BigDecimal;


/**
 * @author phongtt35
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
    private ChiTietSanPhamRepository chiTietSanPhamRepository;
    @Autowired
    private ChucVuRepository chucVuRepository;
    @Autowired
    private SeasonRepository seasonRepository;
    @Autowired
    private GioHangChiTietRepository gioHangChiTietRepository;
    @Autowired
    private GioHangRepository gioHangRepository;
    @Autowired
    private HoaDonRepository hoaDonRepository;
    @Autowired
    private MauSacRepository mauSacRepository;
    @Autowired
    private NhaSanXuatRepository nhaSanXuatRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        //Gen Chức Vụ
        ChucVu chucVu = new ChucVu(null,"CV001","User");
        chucVuRepository.save(chucVu);
        ChucVu chucVu1 = new ChucVu(null,"CV002","Admin");
        chucVuRepository.save(chucVu1);

        //Gen Category
        Category category = new Category(null,"CT001","Quần");
        categoryRepository.save(category);
        Category category2 = new Category(null,"CT002","Áo");
        categoryRepository.save(category2);
        Category category3 = new Category(null,"CT003","Giày");
        categoryRepository.save(category3);

        //Gen Màu Sắc
        MauSac mauSac = new MauSac(null,"MS001","Đỏ");
        mauSacRepository.save(mauSac);
        MauSac mauSac1 = new MauSac(null,"MS002","Xanh");
        mauSacRepository.save(mauSac1);
        MauSac mauSac2 = new MauSac(null,"MS003","Đen");
        mauSacRepository.save(mauSac2);
        MauSac mauSac3 = new MauSac(null,"MS004","Trắng");
        mauSacRepository.save(mauSac3);

        //Gen NhaSan Xuat
        NhaSanXuat nhaSanXuat = new NhaSanXuat(null,"NSX001","Premia");
        nhaSanXuatRepository.save(nhaSanXuat);
        NhaSanXuat nhaSanXuat1 = new NhaSanXuat(null,"NSX002","Congo");
        nhaSanXuatRepository.save(nhaSanXuat1);
        NhaSanXuat nhaSanXuat2 = new NhaSanXuat(null,"NSX003","GiuDas");
        nhaSanXuatRepository.save(nhaSanXuat2);

        //Gen ChiTiet San Pham
        ChiTietSanPham chiTietSanPham = new ChiTietSanPham(null,"Áo 2 màu cho nam và nữ",nhaSanXuat,category2,mauSac2,"Chất Liệu Vải Được làm bằng Cotton mát mẻ không bị ngứa","static/images/ao/ao_polo.webp",200, BigDecimal.valueOf(300));
        chiTietSanPhamRepository.save(chiTietSanPham);
        ChiTietSanPham chiTietSanPham1 = new ChiTietSanPham(null,"Quần Vải Sang Chảnh",nhaSanXuat1,category,mauSac3,"Chất Liệu Vải Được làm bằng Cotton mát mẻ không bị ngứa","static/images/pants/quan_jean.webp",200, BigDecimal.valueOf(150));
        chiTietSanPhamRepository.save(chiTietSanPham1);
        ChiTietSanPham chiTietSanPham2 = new ChiTietSanPham(null,"Giày đẹp cho nam và nữ",nhaSanXuat2,category3,mauSac3,"Giày được làm bằng vải tơ tằm được dệt cách đây 300 năm,thuộc hàng cao cấp và hiếm nhất","static/images/shoes/giay_cho_nam.webp",200, BigDecimal.valueOf(300));
        chiTietSanPhamRepository.save(chiTietSanPham2);

        //Gen User
        User user = new User(null,"US001","Nguyen Dinh Cao","caondph20015@fpt.edu.vn","img",null,chucVu);
        userRepository.save(user);
        User user1 = new User(null,"US002","Cao Coc","caococ2002","img",null,chucVu);
        userRepository.save(user1);

        //Gen Admin
        Admin admin = new Admin(null,"AM001","Nam Dinh","0943232913","namdinh","123456",chucVu1);
        adminRepository.save(admin);
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(DBGenerator.class);
        ctx.close();
    }

}