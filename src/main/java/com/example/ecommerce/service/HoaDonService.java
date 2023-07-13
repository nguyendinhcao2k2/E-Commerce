package com.example.ecommerce.service;

import com.example.ecommerce.model.response.MonthAmountResponse;
import com.example.ecommerce.model.response.ThuAmountResponse;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author caodinh
 */
public interface HoaDonService {
    String getMaxMaHoaDon();

    BigDecimal getTotalPrice();

    int totalInvoice();

    int cancelInvoice();

    List<ThuAmountResponse> getThuAndAmount();

    List<MonthAmountResponse> getAmountByMonth();

    List<ThuAmountResponse> getAmountByYear();

}
