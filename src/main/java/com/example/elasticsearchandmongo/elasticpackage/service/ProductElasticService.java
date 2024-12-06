package com.example.elasticsearchandmongo.elasticpackage.service;

import com.example.elasticsearchandmongo.elasticpackage.dto.ProductElasticDto;
import com.example.elasticsearchtest.exception.ProductElasticNotFoundException;

import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductElasticService {

    ProductElasticDto createProductElastic(ProductElasticDto employeeDto);

    ProductElasticDto getProductElasticById(String employeeId) throws ProductElasticNotFoundException;

    List<ProductElasticDto> getProductElastics();

    void deleteProductElastic(String employeeId) throws ProductElasticNotFoundException;

    ProductElasticDto updateProductElastic( ProductElasticDto employeeDto) throws ProductElasticNotFoundException;

    Page<ProductElasticDto> getPaginatedProductElastics(int page, int size);


}
