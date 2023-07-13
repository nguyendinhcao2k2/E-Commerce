package com.example.ecommerce.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @author caodinh
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ThuAmountResponse {

    private String dataLabel;
    private BigDecimal amount;

}
