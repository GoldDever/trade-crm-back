package ru.javamentor.service.product;

import ru.javamentor.dto.product.ProductCategoryPostDto;

/**
 * Интерфейс для ProductCategory
 */
public interface ProductCategoryService {
    void saveProductCategory(ProductCategoryPostDto productCategoryPostDto);
}
