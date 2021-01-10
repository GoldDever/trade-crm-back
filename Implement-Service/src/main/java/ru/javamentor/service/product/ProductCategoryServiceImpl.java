package ru.javamentor.service.product;

import org.springframework.stereotype.Service;
import ru.javamentor.dto.product.ProductCategoryDto;
import ru.javamentor.dto.product.ProductCategoryPostDto;
import ru.javamentor.model.product.ProductCategory;
import ru.javamentor.repository.product.ProductCategoryRepository;

/**
 * Имплементация интерфейса ProductCategoryService
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService{
    private ProductCategoryRepository productCategoryRepository;

    public ProductCategoryServiceImpl(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    /**
     * Метод сохраняет ProductCategory на основе ProductCategoryPostDto
     * @param productCategoryPostDto
     */
    @Override
    public void saveProductCategory(ProductCategoryPostDto productCategoryPostDto) {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName(productCategoryPostDto.getCategoryName());
        productCategory.setMainCategory(productCategoryPostDto.getMainCategory());
        productCategoryRepository.save(productCategory);
    }
}
