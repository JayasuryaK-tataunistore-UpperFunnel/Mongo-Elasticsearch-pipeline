package com.example.elasticsearchandmongo.mongopackage.dto;


import lombok.Data;

@Data
public class ProductDto {
    private String name;
    private String description;
    private Double price;
    private String category;
    private int inventory;


    public ProductDto() {
    }

    public ProductDto(String name, String description, Double price, String category, int inventory) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.inventory = inventory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }
}
