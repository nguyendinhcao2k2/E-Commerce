package com.example.ecommerce.service.impl;

import com.example.ecommerce.model.response.SizeResponse;
import com.example.ecommerce.repository.SizeRepository;
import com.example.ecommerce.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author caodinh
 */
@Service
public class SizeServiceImpl implements SizeService {

    @Autowired
    private SizeRepository sizeRepository;

    @Override
    public List<SizeResponse> getByTypeClothing() {
        return sizeRepository.getByTypeClothing();
    }

    @Override
    public List<SizeResponse> getByTypeShoe() {
        return sizeRepository.getByTypeShoe();
    }
}
