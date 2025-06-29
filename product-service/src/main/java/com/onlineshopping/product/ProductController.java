package com.onlineshopping.product;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<ApiResponse<List<ProductDTO>>> getProductsByCategory(
            @PathVariable String category,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) Long minPriceId,
            @RequestParam(required = false) Long maxPriceId,
            @RequestHeader(value = "trace-id", required = false) String traceId) {
        return productService.getProductsByCategory(category, sortBy, traceId, page, size, brand, minPriceId, maxPriceId);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProductDTO>> addProduct(@RequestBody Product product, @RequestHeader(value = "trace-id", required = false) String traceId) {
        // Validation: name, brand, description mandatory
        if (product.getName() == null || product.getBrand() == null || product.getDescription() == null) {
            ApiError error = new ApiError("VALIDATION_ERROR", "Name, brand, and description are mandatory.");
            return ResponseEntity.badRequest().body(new ApiResponse<>(traceId, false, null, error));
        }
        Product saved = productService.addProduct(product); // Use service method
        ProductDTO dto = new org.modelmapper.ModelMapper().map(saved, ProductDTO.class);
        dto.setPrice(Double.NaN);
        dto.setInventory(null);
        return ResponseEntity.ok(new ApiResponse<>(traceId, true, dto, null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductDTO>> updateProduct(@PathVariable Long id, @RequestBody Product product, @RequestHeader(value = "trace-id", required = false) String traceId) {
        // Validation: price format, at least one attribute, mandatory fields
        if (product.getName() == null || product.getBrand() == null || product.getDescription() == null) {
            ApiError error = new ApiError("VALIDATION_ERROR", "Name, brand, and description are mandatory.");
            return ResponseEntity.badRequest().body(new ApiResponse<>(traceId, false, null, error));
        }
        ApiResponse<ProductDTO> updateResult = productService.updateProduct(id, product, traceId);
        if (!updateResult.isSuccess()) {
            return ResponseEntity.status(404).body(updateResult);
        }
        return ResponseEntity.ok(updateResult);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProduct(@PathVariable Long id, @RequestHeader(value = "trace-id", required = false) String traceId) {
        ApiResponse<Void> deleteResult = productService.deleteProduct(id, traceId);
        if (!deleteResult.isSuccess()) {
            return ResponseEntity.status(404).body(deleteResult);
        }
        return ResponseEntity.ok(deleteResult);
    }
}
