package com.onlineshopping.inventory;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {
    private String code;
    private String message;
}
