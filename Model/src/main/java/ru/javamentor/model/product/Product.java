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

    @Column(name = "article")
    private String article;

    @Column(name = "purchase_price")
    private BigDecimal purchasePrice;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "margin")
    private BigDecimal margin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id")
    private Unit unit;

    @Column(name = "packaging_count")
    private Integer packagingCount;

    @Column(name = "id_from_erp", unique = true)
    private String idFromErp;

    public Product() {
    }

    public Product(Integer productCount, String productName, String madeCountry,
                   Manufacturer manufacturer, Set<Supplier> suppliers,
                   String article, BigDecimal purchasePrice,
                   BigDecimal price, BigDecimal margin,
                   Unit unit, Integer packagingCount,
                   String idFromErp) {
        this.productCount = productCount;
        this.productName = productName;
        this.madeCountry = madeCountry;
        this.manufacturer = manufacturer;
        this.suppliers = suppliers;
        this.article = article;
        this.purchasePrice = purchasePrice;
        this.price = price;
        this.margin = margin;
        this.unit = unit;
        this.packagingCount = packagingCount;
        this.idFromErp = idFromErp;
    }

    public Product(Long id, Integer productCount, String productName,
                   String madeCountry, Manufacturer manufacturer,
                   Set<Supplier> suppliers, String article,
                   BigDecimal purchasePrice, BigDecimal price,
                   BigDecimal margin, Unit unit,
                   Integer packagingCount, String idFromErp) {
        this.id = id;
        this.productCount = productCount;
        this.productName = productName;
        this.madeCountry = madeCountry;
        this.manufacturer = manufacturer;
        this.suppliers = suppliers;
        this.article = article;
        this.purchasePrice = purchasePrice;
        this.price = price;
        this.margin = margin;
        this.unit = unit;
        this.packagingCount = packagingCount;
        this.idFromErp = idFromErp;
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

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getMargin() {
        return margin;
    }

    public void setMargin(BigDecimal margin) {
        this.margin = margin.setScale(2, RoundingMode.HALF_UP);
    }
}
