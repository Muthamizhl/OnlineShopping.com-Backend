package com.onlineshopping.price;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/prices")
public class PriceController {
    private final PriceRepository priceRepository;

    public PriceController(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Price> getPrice(@PathVariable Long id) {
        Optional<Price> price = priceRepository.findById(id);
        return price.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Price> addPrice(@RequestBody Price price) {
        return ResponseEntity.ok(priceRepository.save(price));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Price> updatePrice(@PathVariable Long id, @RequestBody Price price) {
        if (!priceRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        price.setId(id);
        return ResponseEntity.ok(priceRepository.save(price));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrice(@PathVariable Long id) {
        if (!priceRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        priceRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
