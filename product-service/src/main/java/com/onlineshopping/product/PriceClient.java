package com.onlineshopping.product;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

@Component
public class PriceClient {
    private final RestTemplate restTemplate;
    @Value("${price.service.url:http://localhost:8082/api/prices}")
    private String priceServiceUrl;

    public PriceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Retryable(value = Exception.class, maxAttempts = 3, backoff = @Backoff(delay = 500))
    public Double getPrice(Long priceId) {
        try {
            PriceResponse response = restTemplate.getForObject(priceServiceUrl + "/" + priceId, PriceResponse.class);
            return response != null ? response.getValue() : Double.NaN;
        } catch (Exception e) {
            return Double.NaN;
        }
    }

    public static class PriceResponse {
        private Long id;
        private Double value;
        private String currency;

        public PriceResponse() {}
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public Double getValue() { return value; }
        public void setValue(Double value) { this.value = value; }
        public String getCurrency() { return currency; }
        public void setCurrency(String currency) { this.currency = currency; }
    }
}
