package com.example.ecommerce.service;

import com.example.ecommerce.model.response.SizeResponse;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author caodinh
 */
public interface SizeService {

    List<SizeResponse> getByTypeClothing();

    List<SizeResponse> getByTypeShoe();
}
