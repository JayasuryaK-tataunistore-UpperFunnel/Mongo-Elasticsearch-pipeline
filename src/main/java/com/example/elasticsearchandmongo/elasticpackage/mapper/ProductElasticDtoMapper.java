package com.example.elasticsearchandmongo.elasticpackage.mapper;


import com.example.elasticsearchandmongo.elasticpackage.dto.ProductElasticDto;
import com.example.elasticsearchandmongo.elasticpackage.model.ProductElastic;

public class ProductElasticDtoMapper {

    public static ProductElasticDto mapToProductElasticDto(ProductElastic productElastic) {
        return new ProductElasticDto(productElastic.getProductId(),
                productElastic.getName(),
                productElastic.getDescription() ,
                productElastic.getPrice() ,
                productElastic.getCategory() ,
                productElastic.getInventory());
    }

    public static ProductElastic mapToProductElastic(ProductElasticDto productElasticDto) {
        return new ProductElastic(
                productElasticDto.getName(),
                productElasticDto.getDescription() ,
                productElasticDto.getPrice() ,
                productElasticDto.getCategory() ,
                productElasticDto.getInventory());
    }

}