package ru.javamentor.model.product;

import javax.persistence.*;

@Entity
@Table(name = "product_categories")
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "is_main_category")
    private Boolean mainCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_product_category_id")
    private ProductCategory mainProductCategory;

    private String idFromErp;

    public ProductCategory() {
    }

    public ProductCategory(
            Long id,
            String categoryName,
            Boolean mainCategory,
            ProductCategory mainProductCategory,
            String idFromErp) {
        this.id = id;
        this.categoryName = categoryName;
        this.mainCategory = mainCategory;
        this.mainProductCategory = mainProductCategory;
        this.idFromErp = idFromErp;
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

    public Boolean getMainCategory() {
        return mainCategory;
    }

    public String getIdFromErp() {
        return idFromErp;
    }

    public void setIdFromErp(String idFromErp) {
        this.idFromErp = idFromErp;
    }
}
