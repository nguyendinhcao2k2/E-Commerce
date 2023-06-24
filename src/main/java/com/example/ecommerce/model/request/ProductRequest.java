package com.example.ecommerce.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author caodinh
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductRequest {
    private String tenSP;
    private double price;
    private int amount;
    private String category;
    private String season;
    private String description;
    private List<String> shoeSize;
    private List<String> clothingSize;
    private List<String> colors;
}
