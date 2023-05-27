package com.example.ecommerce.service.impl;

import com.example.ecommerce.model.response.SeasonResponse;
import com.example.ecommerce.repository.SeasonRepository;
import com.example.ecommerce.service.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author caodinh
 */
@Service
public class SeasonServiceImpl implements SeasonService {

    @Autowired
    private SeasonRepository seasonRepository;

    @Override
    public List<SeasonResponse> getAllSeason() {
        return seasonRepository.getAllSeason();
    }
}
