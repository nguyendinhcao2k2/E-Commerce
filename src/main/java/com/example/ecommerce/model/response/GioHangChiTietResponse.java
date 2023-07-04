package com.example.ecommerce.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author caodinh
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GioHangChiTietResponse {

    private String tenSP;
    private String color;
    private String size;
    private BigDecimal donGia;
    private int soLuong;

}
