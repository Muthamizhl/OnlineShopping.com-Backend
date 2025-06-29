package com.onlineshopping.product;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {
    // Changed to package-private for controller access
    final ProductRepository productRepository;
    private final PriceClient priceClient;
    private final InventoryClient inventoryClient;
    private final org.modelmapper.ModelMapper modelMapper = new org.modelmapper.ModelMapper();

    public ProductService(ProductRepository productRepository, PriceClient priceClient, InventoryClient inventoryClient) {
        this.productRepository = productRepository;
        this.priceClient = priceClient;
        this.inventoryClient = inventoryClient;
    }

    // Add Product
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    // Update Product
    public ApiResponse<ProductDTO> updateProduct(Long id, Product product, String traceId) {
        if (!productRepository.existsById(id)) {
            return new ApiResponse<>(traceId, false, null, new ApiError("NOT_FOUND", "Product not found."));
        }
        product.setId(id);
        Product saved = productRepository.save(product);
        ProductDTO dto = modelMapper.map(saved, ProductDTO.class);
        dto.setPrice(Double.NaN);
        dto.setInventory(null);
        return new ApiResponse<>(traceId, true, dto, null);
    }

    // Delete Product
    public ApiResponse<Void> deleteProduct(Long id, String traceId) {
        if (!productRepository.existsById(id)) {
            return new ApiResponse<>(traceId, false, null, new ApiError("NOT_FOUND", "Product not found."));
        }
        productRepository.deleteById(id);
        return new ApiResponse<>(traceId, true, null, null);
    }

    public ResponseEntity<ApiResponse<List<ProductDTO>>> getProductsByCategory(
            String category, String sortBy, String traceId,
            Integer page, Integer size, String brand, Long minPriceId, Long maxPriceId) {
        if (page == null) page = 0;
        if (size == null) size = 10;
        if (brand == null) brand = "";
        if (minPriceId == null) minPriceId = 0L;
        if (maxPriceId == null) maxPriceId = Long.MAX_VALUE;
        org.springframework.data.domain.Pageable pageable = org.springframework.data.domain.PageRequest.of(page, size);
        var productsPage = productRepository.findByCategoryAndBrandContainingIgnoreCaseAndPriceIdBetween(
                category, brand, minPriceId, maxPriceId, pageable);
        List<ProductDTO> result = new java.util.ArrayList<>();
        for (Product product : productsPage.getContent()) {
            Double price = priceClient.getPrice(product.getPriceId());
            Integer inventory = inventoryClient.getAvailable(product.getInventoryId());
            if (inventory == null || inventory <= 0) continue;
            ProductDTO dto = modelMapper.map(product, ProductDTO.class);
            dto.setPrice(price);
            dto.setInventory(inventory);
            result.add(dto);
        }
        // Sorting (in-memory, can be improved)
        if ("price".equalsIgnoreCase(sortBy)) {
            result.sort(java.util.Comparator.comparing(ProductDTO::getPrice, java.util.Comparator.nullsLast(Double::compareTo)));
        } else if ("inventory".equalsIgnoreCase(sortBy)) {
            result.sort(java.util.Comparator.comparing(ProductDTO::getInventory, java.util.Comparator.nullsLast(Integer::compareTo)).reversed());
        }
        ApiResponse<List<ProductDTO>> response;
        if (result.isEmpty()) {
            response = new ApiResponse<>(traceId, false, null, new ApiError("NO_PRODUCTS", "No products available with sufficient inventory."));
            return ResponseEntity.status(404).body(response);
        }
        response = new ApiResponse<>(traceId, true, result, null);
        return ResponseEntity.ok(response);
    }
}
