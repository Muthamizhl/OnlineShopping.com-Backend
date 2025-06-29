package com.onlineshopping.price;

import javax.persistence.*;

@Entity
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double priceValue;
    private String currency;

    public Price() {}

    public Price(Long id, Double priceValue, String currency) {
        this.id = id;
        this.priceValue = priceValue;
        this.currency = currency;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Double getPriceValue() {
        return priceValue;
    }
    public void setPriceValue(Double priceValue) {
        this.priceValue = priceValue;
    }
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
