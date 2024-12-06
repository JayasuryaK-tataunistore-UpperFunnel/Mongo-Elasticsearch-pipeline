package com.example.elasticsearchandmongo.mongopackage.controller;


import com.example.elasticsearchandmongo.mongopackage.dto.ProductDto;
import com.example.elasticsearchandmongo.mongopackage.exception.ResourceNotFoundException;
import com.example.elasticsearchandmongo.mongopackage.model.Product;
import com.example.elasticsearchandmongo.mongopackage.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {


    @Autowired
    private ProductService productService;




    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody ProductDto productDto)
    {
        try {
            return productService.addProduct(productDto);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @GetMapping
    private List<Product> getAllTasks()
    {
        return productService.findAllProducts();
    }

    @GetMapping("/{taskId}")
    public Product getTask(@PathVariable String taskId)
    {
        try {
            return productService.findProductById(taskId);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }


    @GetMapping("/severity/{severity}")
    public List<Product> findProductByCategory(@PathVariable String  category)
    {

        try {
            return  productService.findProductByCategory(category);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @GetMapping("/assignee/{assignee}")
    public List<Product> findProductByName(@PathVariable String name)
    {

        try {
            return  productService.findProductByName(name);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @PutMapping
    public Product modifyTask(@RequestBody Product product){
        try {
            return  productService.updateProduct(product);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{taskId}")
    public String deleteProduct(@PathVariable String productId){
        productService.deleteProduct(productId);
        return "deleted";
    }

}
