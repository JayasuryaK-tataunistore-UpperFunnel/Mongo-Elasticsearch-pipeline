package com.example.elasticsearchandmongo.mongotoelastic.controller;


import com.example.elasticsearchandmongo.elasticpackage.model.ProductElastic;
import com.example.elasticsearchandmongo.elasticpackage.repository.ProductElasticRepository;
import com.example.elasticsearchandmongo.elasticpackage.service.ProductElasticService;
import com.example.elasticsearchandmongo.mongopackage.model.Product;
import com.example.elasticsearchandmongo.mongopackage.service.ProductService;
import com.example.elasticsearchandmongo.mongotoelastic.dto.MongoToElasticDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataPipeController {

    @Autowired
    private ProductElasticService productElasticService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductElasticRepository productElasticRepository;

    @PostMapping("/flowtoelastic-from-mongo")
    ResponseEntity<ProductElastic> mongotoElastic(@RequestParam String productId)
    {
        try {
            Product product =  productService.findProductById(productId);
            ProductElastic productElastic = MongoToElasticDto.mapper(product);
            ProductElastic createdProductElastic = productElasticRepository.save(productElastic);
            return new ResponseEntity<>(createdProductElastic, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
