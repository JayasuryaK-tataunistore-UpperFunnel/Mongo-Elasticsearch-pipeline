package com.example.elasticsearchandmongo.mongopackage.repository;

import com.example.elasticsearchandmongo.mongopackage.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product , String> {
    Optional<List<Product>> findByCategory(String category);

    Optional<List<Product>> findByName(String name);
}
