package com.example.ecommerce.service.impl;

import com.example.ecommerce.model.response.MauSacResponse;
import com.example.ecommerce.repository.MauSacRepository;
import com.example.ecommerce.service.MauSacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author caodinh
 */
@Service
public class MauSacServiceImpl implements MauSacService {

    @Autowired
    private MauSacRepository mauSacRepository;

    @Override
    public List<MauSacResponse> getMauSac() {
        return mauSacRepository.getMauSac();
    }

}
