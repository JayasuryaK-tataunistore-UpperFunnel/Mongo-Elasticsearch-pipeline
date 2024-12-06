package com.example.elasticsearchandmongo.mongopackage.service;

import com.example.elasticsearchandmongo.mongopackage.dto.ProductDto;
import com.example.elasticsearchandmongo.mongopackage.model.Product;

import java.util.List;

public interface ProductService {




    Product addProduct(ProductDto p);

    List<Product> findAllProducts();

    Product findProductById(String id);

    List<Product> findProductByCategory(String category);

    List<Product> findProductByName(String name);

    Product updateProduct(Product productRequest);

    void   deleteProduct(String productId);
}
