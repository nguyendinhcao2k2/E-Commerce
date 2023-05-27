package com.example.ecommerce.repository;


import com.example.ecommerce.entity.Season;
import com.example.ecommerce.model.response.SeasonResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author caodinh
 */
public interface SeasonRepository extends JpaRepository<Season,String> {
    @Query("select new com.example.ecommerce.model.response.SeasonResponse(s.id,s.ten,s.img) from Season as s")
    List<SeasonResponse> getAllSeason();
}
