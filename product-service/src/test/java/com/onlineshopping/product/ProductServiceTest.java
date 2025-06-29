package com.onlineshopping.product;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    public ProductServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getProductsByCategory_returnsEmptyList() {
        // Mock the repository to return an empty page (not null)
        org.springframework.data.domain.Page<Product> emptyPage = new org.springframework.data.domain.PageImpl<>(Collections.emptyList(), org.springframework.data.domain.PageRequest.of(0, 10), 0);
        when(productRepository.findByCategoryAndBrandContainingIgnoreCaseAndPriceIdBetween(
            eq("NonExistingCategory"), anyString(), anyLong(), anyLong(), any()))
            .thenReturn(emptyPage);

        ResponseEntity<ApiResponse<List<ProductDTO>>> response = productService.getProductsByCategory(
            "NonExistingCategory", null, "trace-123", 0, 10, "", 0L, Long.MAX_VALUE);
        assertNotNull(response);
        // The service returns success=false when no products are found, so expect false
        assertFalse(response.getBody().isSuccess());
    }
}
