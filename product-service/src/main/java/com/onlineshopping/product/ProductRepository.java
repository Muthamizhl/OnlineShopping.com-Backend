package com.onlineshopping.product;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(String category);

    // Paging and filtering
    Page<Product> findByCategoryAndBrandContainingIgnoreCaseAndPriceIdBetween(
        String category, String brand, Long minPriceId, Long maxPriceId, Pageable pageable);
}
