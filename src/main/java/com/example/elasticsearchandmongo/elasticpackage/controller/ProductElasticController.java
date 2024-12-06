package com.example.elasticsearchandmongo.elasticpackage.controller;


import com.example.elasticsearchtest.exception.ProductElasticNotFoundException;
import com.example.elasticsearchandmongo.elasticpackage.dto.ProductElasticDto;
import com.example.elasticsearchandmongo.elasticpackage.service.ProductElasticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ProductElasticController {

    @Autowired
    private ProductElasticService productElasticService;

    @PostMapping(value = "/productelastic")
    public ResponseEntity<ProductElasticDto> createProductElastic(@RequestBody ProductElasticDto productElasticDto) {
        ProductElasticDto createdProductElastic = productElasticService.createProductElastic(productElasticDto);
        return new ResponseEntity<>(createdProductElastic, HttpStatus.CREATED);
    }

    @GetMapping(value = "/productelastic/{productId}")
    public ResponseEntity<ProductElasticDto> getProductElasticById(@PathVariable("productId") String productId)
            throws ProductElasticNotFoundException {
        try {
            ProductElasticDto productElastic = productElasticService.getProductElasticById(productId);
            return new ResponseEntity<>(productElastic, HttpStatus.OK);
        } catch (ProductElasticNotFoundException productElasticNotFoundException) {
            throw productElasticNotFoundException;
        }
    }


    @GetMapping(value = "/productelastics")
    public ResponseEntity<List<ProductElasticDto>> getProductElastics() {
        List<ProductElasticDto> productElastics = productElasticService.getProductElastics();
        return new ResponseEntity<>(productElastics, HttpStatus.OK);
    }

    @DeleteMapping(value = "/productelastic/{productId}")
    public ResponseEntity<HttpStatus> deleteProductElastic(@PathVariable("productId") String productId)
            throws ProductElasticNotFoundException {
        productElasticService.deleteProductElastic(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/productelastic")
    public ResponseEntity<ProductElasticDto> updateProductElastic(@RequestBody ProductElasticDto employeeDto)
            throws ProductElasticNotFoundException {
        ProductElasticDto createdEmployee = productElasticService.updateProductElastic(employeeDto);
        return new ResponseEntity<>(createdEmployee, HttpStatus.OK);
    }


    @GetMapping(value = "/productelasticpage")
    public ResponseEntity<Page<ProductElasticDto>> getPaginatedProductElastics(@RequestParam int pageNo, @RequestParam int size) {
        Page<ProductElasticDto> pageProductElastics = productElasticService.getPaginatedProductElastics(pageNo, size);
        return new ResponseEntity<>(pageProductElastics, HttpStatus.OK);
    }

}