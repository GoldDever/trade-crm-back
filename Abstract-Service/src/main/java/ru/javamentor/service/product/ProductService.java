package ru.javamentor.service.product;

import ru.javamentor.dto.product.ProductDto;
import ru.javamentor.dto.product.ProductPostDto;


/**
 * Abstract-Service для Продукта
 */
public interface ProductService {

    void saveProduct(ProductPostDto dto);
    void updateProduct(ProductPostDto dto);
}
