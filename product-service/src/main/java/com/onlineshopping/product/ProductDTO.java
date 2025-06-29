

package com.onlineshopping.product;

public class ProductDTO {
    private Long id;
    private String name;
    private String brand;
    private String description;
    private String category;
    private Double price;
    private Integer inventory;

    public ProductDTO() {}

    public ProductDTO(Long id, String name, String brand, String description, String category, Double price, Integer inventory) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.description = description;
        this.category = category;
        this.price = price;
        this.inventory = inventory;
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
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    public Integer getInventory() { return inventory; }
    public void setInventory(Integer inventory) { this.inventory = inventory; }
}
