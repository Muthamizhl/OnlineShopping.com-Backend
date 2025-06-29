package com.onlineshopping.product;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

@Component
public class InventoryClient {
    private final RestTemplate restTemplate;
    @Value("${inventory.service.url:http://localhost:8083/api/inventory}")
    private String inventoryServiceUrl;

    public InventoryClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Retryable(value = Exception.class, maxAttempts = 3, backoff = @Backoff(delay = 500))
    public Integer getAvailable(Long inventoryId) {
        try {
            InventoryResponse response = restTemplate.getForObject(inventoryServiceUrl + "/" + inventoryId, InventoryResponse.class);
            return response != null ? response.getAvailable() : null;
        } catch (Exception e) {
            return null;
        }
    }

    public static class InventoryResponse {
        private Long id;
        private Integer available;
        private Integer reserved;

        public InventoryResponse() {}
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public Integer getAvailable() { return available; }
        public void setAvailable(Integer available) { this.available = available; }
        public Integer getReserved() { return reserved; }
        public void setReserved(Integer reserved) { this.reserved = reserved; }
    }
}
