package ru.javamentor.service;

import ru.javamentor.dto.product.ProductDto;
import ru.javamentor.dto.product.ProductPostDto;


/**
 * Abstract-Service для Продукта
 */
public interface ProductService {

    void saveProduct(ProductPostDto dto);
    void updateProduct(ProductDto dto);
}
