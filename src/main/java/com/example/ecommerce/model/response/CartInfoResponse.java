package com.example.ecommerce.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author caodinh
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CartInfoResponse {

    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phoneNumber;
    private String note;

}
