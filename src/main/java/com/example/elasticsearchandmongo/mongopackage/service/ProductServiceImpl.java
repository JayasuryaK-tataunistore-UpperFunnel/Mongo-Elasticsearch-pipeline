package com.example.elasticsearchandmongo.mongopackage.service;



import com.example.elasticsearchandmongo.mongopackage.dto.ProductDto;
import com.example.elasticsearchandmongo.mongopackage.exception.ResourceNotFoundException;
import com.example.elasticsearchandmongo.mongopackage.model.Product;
import com.example.elasticsearchandmongo.mongopackage.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private  ProductRepository productRepository;




    @Override
    public Product addProduct(ProductDto p)
    {
        String id = UUID.randomUUID().toString().split("-")[0];
        Product product =new Product();
        product.setProductId(id);
        product.setName(p.getName());
        product.setDescription(p.getDescription());
        product.setCategory(p.getCategory());
        product.setPrice(p.getPrice());
        product.setInventory(p.getInventory());

        return productRepository.save(product);
    }

    @Override
    public List<Product> findAllProducts()
    {
        return productRepository.findAll();
    }


    @Override
    public Product findProductById(String id)
    {
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Document not found"));
    }


    @Override
    public List<Product> findProductByCategory(String category)
    {
        return  productRepository.findByCategory(category).orElseThrow(() -> new ResourceNotFoundException("Document not found"));
    }

    @Override
    public List<Product> findProductByName(String name)
    {
        return  productRepository.findByName(name).orElseThrow(() -> new ResourceNotFoundException("Document not found"));
    }

    @Override
    public Product updateProduct(Product productRequest)
    {
        //get existing doc in mongo
        try {
            Product product =findProductById(productRequest.getProductId());
            product.setProductId(productRequest.getProductId());
            product.setName(productRequest.getName());
            product.setDescription(productRequest.getDescription());
            product.setCategory(productRequest.getCategory());
            product.setPrice(productRequest.getPrice());
            product.setInventory(productRequest.getInventory());

            return productRepository.save(product);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }

    }

    @Override
    public  void   deleteProduct(String taskId){
        productRepository.deleteById(taskId);
    }

}
