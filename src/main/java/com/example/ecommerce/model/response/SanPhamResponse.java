package com.example.ecommerce.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author caodinh
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SanPhamResponse {

    private String id;
    private String tenSP;
    private String img;
    private BigDecimal giaBan;
    private String moTa;
}
