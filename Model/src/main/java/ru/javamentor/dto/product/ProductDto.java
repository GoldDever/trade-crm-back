package ru.javamentor.dto.product;

/**
 * DTO для передачи на страницу продукта
 */
public class ProductDto {
    private Long id;
    private String productName;
    private String madeCountry;
    private Long manufacturerId;
    private Long supplierId;
    private String article;
    private Double purchasePrice;
    private Double price;
    private Double margin;
    private Long unitId;
    private Integer packagingCount;

    public ProductDto() {
    }

    public ProductDto(Long id, String productName, String madeCountry, Long manufacturerId,
                      Long supplierId, String article, Double purchasePrice, Double price,
                      Double margin, Long unitId, Integer packagingCount) {
        this.id = id;
        this.productName = productName;
        this.madeCountry = madeCountry;
        this.manufacturerId = manufacturerId;
        this.supplierId = supplierId;
        this.article = article;
        this.purchasePrice = purchasePrice;
        this.price = price;
        this.margin = margin;
        this.unitId = unitId;
        this.packagingCount = packagingCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Long manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getMargin() {
        return margin;
    }

    public void setMargin(Double margin) {
        this.margin = margin;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public Integer getPackagingCount() {
        return packagingCount;
    }

    public void setPackagingCount(Integer packagingCount) {
        this.packagingCount = packagingCount;
    }
}