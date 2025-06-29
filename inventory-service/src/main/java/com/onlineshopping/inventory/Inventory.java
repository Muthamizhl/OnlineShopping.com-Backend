package com.onlineshopping.inventory;

import javax.persistence.*;

@Entity
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer available;
    private Integer reserved;

    public Inventory() {}

    public Inventory(Long id, Integer available, Integer reserved) {
        this.id = id;
        this.available = available;
        this.reserved = reserved;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Integer getAvailable() {
        return available;
    }
    public void setAvailable(Integer available) {
        this.available = available;
    }
    public Integer getReserved() {
        return reserved;
    }
    public void setReserved(Integer reserved) {
        this.reserved = reserved;
    }
}
