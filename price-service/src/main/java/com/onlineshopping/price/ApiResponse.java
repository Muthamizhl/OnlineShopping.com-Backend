package com.onlineshopping.price;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private String traceId;
    private boolean success;
    private T data;
    private ApiError error;
}
