package com.example.elasticsearchtest.exception;


public class ProductElasticNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public ProductElasticNotFoundException() {
    }

    public ProductElasticNotFoundException(String message) {
        super(message);
    }
}
