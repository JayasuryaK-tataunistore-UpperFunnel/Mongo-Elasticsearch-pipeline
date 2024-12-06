package com.example.elasticsearchandmongo.elasticpackage.service;





import com.example.elasticsearchandmongo.elasticpackage.mapper.ProductElasticDtoMapper;
import com.example.elasticsearchandmongo.elasticpackage.model.ProductElastic;
import com.example.elasticsearchtest.exception.ProductElasticNotFoundException;

import com.example.elasticsearchandmongo.elasticpackage.dto.ProductElasticDto;
import com.example.elasticsearchandmongo.elasticpackage.repository.ProductElasticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class ProductElasticServiceImpl implements ProductElasticService {

    @Autowired
    private ProductElasticRepository productElasticRepository;

    @Override
    public ProductElasticDto createProductElastic(ProductElasticDto productElasticDto) {
        ProductElastic productElastic = ProductElasticDtoMapper.mapToProductElastic(productElasticDto);
        ProductElastic createdProductElastic = productElasticRepository.save(productElastic);
        return ProductElasticDtoMapper.mapToProductElasticDto(createdProductElastic);
    }

    @Override
    public ProductElasticDto getProductElasticById(String productId) throws ProductElasticNotFoundException {
        Optional<ProductElastic> productElastic = productElasticRepository.findById(productId);
        if (productElastic.isEmpty()) {
            throw new ProductElasticNotFoundException("Employee with id - " + productId + " not found.");
        }
        return ProductElasticDtoMapper.mapToProductElasticDto(productElastic.get());
    }

    @Override
    public List<ProductElasticDto> getProductElastics() {
        Iterable<ProductElastic> productElasticsIterable = productElasticRepository.findAll();
        List<ProductElastic> productElastics = StreamSupport.stream(productElasticsIterable.spliterator(), false).toList();
        return productElastics.stream().map((prd) -> ProductElasticDtoMapper.mapToProductElasticDto(prd)).collect(Collectors.toList());
    }

    @Override
    public void deleteProductElastic(String productId) throws ProductElasticNotFoundException {
        Optional<ProductElastic> productElastic = productElasticRepository.findById(productId);
        if (productElastic.isEmpty()) {
            throw new ProductElasticNotFoundException("Employee with id - " + productId + " not found.");
        }
        productElasticRepository.deleteById(productId);
    }

    @Override
    public ProductElasticDto updateProductElastic(ProductElasticDto productElasticDto) throws  ProductElasticNotFoundException{
        Optional<ProductElastic> retrievedEmployee = productElasticRepository.findById(productElasticDto.getProductId());
        if (retrievedEmployee.isEmpty()) {
            throw new ProductElasticNotFoundException("Employee with id - " + productElasticDto.getProductId() + " not found.");
        }
        ProductElastic productElastic = retrievedEmployee.get();
        productElastic.setDescription(productElasticDto.getDescription());
        productElastic.setName(productElasticDto.getName());
        ProductElastic createdProductElastic = productElasticRepository.save(productElastic);
        return ProductElasticDtoMapper.mapToProductElasticDto(createdProductElastic);
    }


    @Override
    public Page<ProductElasticDto> getPaginatedProductElastics(int pageNo, int size) {
        PageRequest pageable = PageRequest.of(pageNo, size);
        Page<ProductElastic> productElasticPage = productElasticRepository.findAll(pageable);
        Page<ProductElasticDto> productElasticDtoPage = productElasticPage.map(ProductElasticDtoMapper::mapToProductElasticDto);
        return productElasticDtoPage;
    }


}
