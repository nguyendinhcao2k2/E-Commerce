package com.example.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author caodinh
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CategoryResponse {
    private String id;
    private String ten;
    private String img;
    private Long amountProduct;
}
