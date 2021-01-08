package ru.javamentor.dto.product;

import ru.javamentor.model.product.ProductCategory;

/**
 * DTO для создания новой категории
 */

public class ProductCategoryDto {
    Long id;
    String categoryName;
    Boolean isMainCategory;
    ProductCategory mainProductCategory;

    public ProductCategoryDto() {
    }

    public ProductCategoryDto(String categoryName, Boolean isMainCategory, ProductCategory mainProductCategory) {
        this.categoryName = categoryName;
        this.isMainCategory = isMainCategory;
        this.mainProductCategory = mainProductCategory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Boolean getMainCategory() {
        return isMainCategory;
    }

    public void setMainCategory(Boolean mainCategory) {
        isMainCategory = mainCategory;
    }

    public ProductCategory getMainProductCategory() {
        return mainProductCategory;
    }

    public void setMainProductCategory(ProductCategory mainProductCategory) {
        this.mainProductCategory = mainProductCategory;
    }
}
