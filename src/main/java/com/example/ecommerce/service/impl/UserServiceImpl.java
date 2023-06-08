package com.example.ecommerce.service.impl;

import com.example.ecommerce.entity.User;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.sercurity.CustomOAuth2User;
import com.example.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author caodinh
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void processOAuthPostLogin(CustomOAuth2User customOAuth2User) {
        User existUser = userRepository.getUserByUsername(customOAuth2User.getEmail());
        if (existUser == null) {
            User newUser = new User();
            newUser.setEmail(customOAuth2User.getEmail());
            newUser.setHoTen(customOAuth2User.getName());
            newUser.setImg(customOAuth2User.getPicture());
            newUser.setMa(getMaxMaUser());
            userRepository.save(newUser);
        }
    }

    @Override
    public String getMaxMaUser() {
        String maUS = null;
        try {
            maUS = userRepository.getMaxMaUser();
        } catch (Exception e) {
            // Xử lý ngoại lệ
        }

        if (maUS == null) {
            maUS = "1";
            int ma = Integer.valueOf(maUS);
            maUS = "US00" + ma;
            return maUS;
        }

        int ma = Integer.valueOf(maUS);
        ma++;
        maUS = "US00" + ma;
        return maUS;
    }
}
