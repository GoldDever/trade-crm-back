package ru.javamentor.model.product;

import javax.persistence.*;

@Entity
@Table(name = "product_categories")
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "is_main_category")
    private Boolean mainCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_product_category_id")
    private ProductCategory mainProductCategory;

    public ProductCategory() {
    }

    public ProductCategory(String categoryName, Boolean mainCategory, ProductCategory mainProductCategory) {
        this.categoryName = categoryName;
        this.mainCategory = mainCategory;
        this.mainProductCategory = mainProductCategory;
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

    public Boolean isMainCategory() {
        return mainCategory;
    }

    public void setMainCategory(Boolean mainCategory) {
        this.mainCategory = mainCategory;
    }

    public ProductCategory getMainProductCategory() {
        return mainProductCategory;
    }

    public void setMainProductCategory(ProductCategory mainProductCategory) {
        this.mainProductCategory = mainProductCategory;
    }
}
