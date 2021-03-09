package ru.javamentor.dto.product;

/**
 * DTO для для передачи категории продукта
 */
public class ProductCategoryDto {
    private Long id;
    private String categoryName;
    private Boolean mainCategory;
    private Long mainProductCategory;

    public ProductCategoryDto(
            Long id,
            String categoryName,
            Long mainProductCategory) {
        this.id = id;
        this.categoryName = categoryName;
        this.mainProductCategory = mainProductCategory;
        this.mainCategory = this.mainProductCategory == null;
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
        return mainCategory;
    }

    public void setMainCategory(Boolean mainCategory) {
        this.mainCategory = mainCategory;
    }

    public Long getMainProductCategory() {
        return mainProductCategory;
    }

    public void setMainProductCategory(Long mainProductCategory) {
        this.mainProductCategory = mainProductCategory;
    }
}
