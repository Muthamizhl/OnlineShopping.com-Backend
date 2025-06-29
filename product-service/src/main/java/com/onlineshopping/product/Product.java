

package com.onlineshopping.product;

import javax.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String brand;
    private String description;
    private String category;
    private Long priceId;
    private Long inventoryId;

    public Product() {}

    public Product(Long id, String name, String brand, String description, String category, Long priceId, Long inventoryId) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.description = description;
        this.category = category;
        this.priceId = priceId;
        this.inventoryId = inventoryId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public Long getPriceId() { return priceId; }
    public void setPriceId(Long priceId) { this.priceId = priceId; }
    public Long getInventoryId() { return inventoryId; }
    public void setInventoryId(Long inventoryId) { this.inventoryId = inventoryId; }
}
