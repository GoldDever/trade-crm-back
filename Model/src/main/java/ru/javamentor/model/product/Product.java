package ru.javamentor.model.product;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "product_count")
    private Integer productCount;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "made_country")
    private String madeCountry;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "products_suppliers",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "supplier_id"))
    private Set<Supplier> suppliers;

    @Column(name = "article", unique = true)
    private String article;

    @Column(name = "min_margin")
    private BigDecimal minMargin;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "standard_margin")
    private BigDecimal standardMargin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id")
    private Unit unit;

    @Column(name = "packaging_count")
    private Integer packagingCount;

    @Column(name = "id_from_erp", unique = true)
    private String idFromErp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_category_id")
    ProductCategory productCategory;

    @Column(name = "image_url")
    private String ImageUrl;

    public Product() {
    }

    public Product(Integer productCount, String productName, String madeCountry,
                   Manufacturer manufacturer, Set<Supplier> suppliers,
                   String article, BigDecimal minMargin,
                   BigDecimal price, BigDecimal standardMargin,
                   Unit unit, Integer packagingCount,
                   String idFromErp, ProductCategory productCategory) {
        this.productCount = productCount;
        this.productName = productName;
        this.madeCountry = madeCountry;
        this.manufacturer = manufacturer;
        this.suppliers = suppliers;
        this.article = article;
        this.minMargin = minMargin;
        this.price = price;
        this.standardMargin = standardMargin;
        this.unit = unit;
        this.packagingCount = packagingCount;
        this.idFromErp = idFromErp;
        this.productCategory = productCategory;
    }

    public Product(Long id, Integer productCount, String productName,
                   String madeCountry, Manufacturer manufacturer,
                   Set<Supplier> suppliers, String article,
                   BigDecimal minMargin, BigDecimal price,
                   BigDecimal standardMargin, Unit unit,
                   Integer packagingCount, String idFromErp, ProductCategory productCategory) {
        this.id = id;
        this.productCount = productCount;
        this.productName = productName;
        this.madeCountry = madeCountry;
        this.manufacturer = manufacturer;
        this.suppliers = suppliers;
        this.article = article;
        this.minMargin = minMargin;
        this.price = price;
        this.standardMargin = standardMargin;
        this.unit = unit;
        this.packagingCount = packagingCount;
        this.idFromErp = idFromErp;
        this.productCategory = productCategory;
    }


    public Product(Integer productCount, String productName,
                   String madeCountry, Manufacturer manufacturer,
                   Set<Supplier> suppliers, String article,
                   BigDecimal minMargin, BigDecimal price,
                   BigDecimal standardMargin, Unit unit,
                   Integer packagingCount, ProductCategory productCategory) {
        this.productCount = productCount;
        this.productName = productName;
        this.madeCountry = madeCountry;
        this.manufacturer = manufacturer;
        this.suppliers = suppliers;
        this.article = article;
        this.minMargin = minMargin;
        this.price = price;
        this.standardMargin = standardMargin;
        this.unit = unit;
        this.packagingCount = packagingCount;
        this.productCategory = productCategory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getMadeCountry() {
        return madeCountry;
    }

    public void setMadeCountry(String madeCountry) {
        this.madeCountry = madeCountry;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Set<Supplier> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(Set<Supplier> suppliers) {
        this.suppliers = suppliers;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Integer getPackagingCount() {
        return packagingCount;
    }

    public void setPackagingCount(Integer packagingCount) {
        this.packagingCount = packagingCount;
    }

    public String getIdFromErp() {
        return idFromErp;
    }

    public void setIdFromErp(String idFromErp) {
        this.idFromErp = idFromErp;
    }

    public BigDecimal getMinMargin() {
        return minMargin;
    }

    public void setMinMargin(BigDecimal minMargin) {
        this.minMargin = minMargin.setScale(2, RoundingMode.HALF_UP);
    }


    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getStandardMargin() {
        return standardMargin;
    }

    public void setStandardMargin(BigDecimal margin) {
        this.standardMargin = margin.setScale(2, RoundingMode.HALF_UP);
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }
}
