package ru.javamentor.service.product;

public class ProductServiceException extends RuntimeException {
    public ProductServiceException() {
    }

    public ProductServiceException(String message) {
        super(message);
    }
}

