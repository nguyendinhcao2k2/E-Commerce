package com.example.ecommerce.service;

import com.example.ecommerce.sercurity.CustomOAuth2User;

/**
 * @author caodinh
 */
public interface UserService {

    void processOAuthPostLogin(CustomOAuth2User customOAuth2User);

    String getMaxMaUser();

}
