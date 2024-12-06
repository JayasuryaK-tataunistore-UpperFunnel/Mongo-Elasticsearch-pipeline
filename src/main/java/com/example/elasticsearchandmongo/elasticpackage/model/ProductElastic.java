package com.example.elasticsearchandmongo.elasticpackage.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "products")
public class ProductElastic {

    @Id
    private String productId;
    private String name;
    private String description;
    private Double price;
    private String category;
    private int inventory;

    public ProductElastic(String productId, String name, String description, Double price, String category, int inventory) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.inventory = inventory;
    }

    public ProductElastic() {
    }

    public ProductElastic(String name, String description, Double price, String category, int inventory) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.inventory = inventory;
    }




    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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