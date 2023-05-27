package com.example.ecommerce.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author caodinh
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SeasonResponse {
    private String id;
    private String name;
    private String img;
}
