package ru.javamentor.dto.product;

import ru.javamentor.model.product.ProductCategory;

/**
 * DTO для создания новой категории
 */
public class ProductCategoryPostDto {
    String categoryName;
    Boolean isMainCategory;
    ProductCategory mainProductCategory;

    public ProductCategoryPostDto() {
    }

    public ProductCategoryPostDto(String categoryName, Boolean isMainCategory, ProductCategory mainProductCategory) {
        this.categoryName = categoryName;
        this.isMainCategory = isMainCategory;
        this.mainProductCategory = mainProductCategory;
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
