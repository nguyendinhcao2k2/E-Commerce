package com.example.ecommerce.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * @author caodinh
 */
@Component
public class ImageUtil {

    private static ResourceLoader resourceLoader;

    @Autowired
    public ImageUtil(ResourceLoader resourceLoader) {
        ImageUtil.resourceLoader = resourceLoader;
    }

    public static boolean deleteImage(String fileName) throws IOException {
        try {
            String path = "static/img" + fileName;
            ClassPathResource resource = new ClassPathResource(path);
            String uploadDir = resource.getFile().getAbsolutePath();
            System.out.println(uploadDir);
            Path filePath = Paths.get(uploadDir);
            // Xóa tệp tin ảnh
            Files.delete(filePath);

            return true; // Trả về true nếu xóa thành công
        } catch (IOException e) {
            e.printStackTrace();
            return false; // Trả về false nếu xóa không thành công hoặc có lỗi xảy ra
        }
    }


    public static String saveImage(MultipartFile file, String path) throws IOException {
        if (!file.isEmpty()) {
            ClassPathResource resource = new ClassPathResource(path);
            String uploadDir = resource.getFile().getAbsolutePath();

            // Tạo tên tệp tin duy nhất cho ảnh
            String fileName = generateUniqueFileName(file.getOriginalFilename());

            // Tạo đường dẫn đến tệp tin ảnh
            Path filePath = Path.of(uploadDir, fileName);

            // Copy nội dung của tệp tin ảnh vào đường dẫn đã chỉ định
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Thực hiện xử lý khác nếu cần

            return fileName; // Trả về tên tệp tin ảnh đã lưu
        }

        return null;
    }

    public static String generateUniqueFileName(String originalFilename) {
        // Lấy phần mở rộng của tệp tin ảnh (ví dụ: jpg, png, ...)
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));

        // Tạo một tên tệp tin duy nhất bằng UUID và thời gian hiện tại
        String uniqueFileName = UUID.randomUUID().toString() +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) +
                fileExtension;

        return uniqueFileName;
    }
}
