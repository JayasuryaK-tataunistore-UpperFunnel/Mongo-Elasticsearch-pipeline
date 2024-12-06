package com.example.elasticsearchandmongo.mongotoelastic.dto;

import com.example.elasticsearchandmongo.elasticpackage.model.ProductElastic;
import com.example.elasticsearchandmongo.mongopackage.model.Product;

public class MongoToElasticDto {

    public static ProductElastic mapper(Product product)
    {
        return new ProductElastic(
                product.getName(),
                product.getDescription() ,
                product.getPrice() ,
                product.getCategory() ,
                product.getInventory()
        );
    }
}
