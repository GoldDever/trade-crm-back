package ru.javamentor.model.product;

import javax.persistence.*;

@Entity
@Table(name = "product_categories")
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long Id;

    @Column(name = "category_name")
    String categoryName;

    @Column(name = "is_main_category")
    Boolean isMainCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_product_category_id")
    ProductCategory mainProductCategory;

    public ProductCategory() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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
