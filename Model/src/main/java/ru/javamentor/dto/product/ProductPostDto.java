package ru.javamentor.dto.product;

import ru.javamentor.model.product.ProductCategory;

import java.util.List;

/**
 * DTO для создания нового продукта
 */
public class ProductPostDto {
    private Integer productCount;
    private String productName;
    private String madeCountry;
    private ManufacturerDto manufacturerDto;
    private List<SupplierDto> supplierDto;
    private String article;
    private Double purchasePrice;
    private Double price;
    private Double margin;
    private UnitDto unitDto;
    private Integer packagingCount;
    private String idFromErp;
    private ProductCategory productCategory;

    public ProductPostDto() {
    }

    public ProductPostDto(Integer productCount, String productName, String madeCountry,
                          ManufacturerDto manufacturerDto, List<SupplierDto> supplierDto,
                          String article, Double purchasePrice,
                          Double price, Double margin,
                          UnitDto unitDto, Integer packagingCount,
                          String idFromErp, ProductCategory productCategory) {
        this.productCount = productCount;
        this.productName = productName;
        this.madeCountry = madeCountry;
        this.manufacturerDto = manufacturerDto;
        this.supplierDto = supplierDto;
        this.article = article;
        this.purchasePrice = purchasePrice;
        this.price = price;
        this.margin = margin;
        this.unitDto = unitDto;
        this.packagingCount = packagingCount;
        this.idFromErp = idFromErp;
        this.productCategory = productCategory;
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

    public ManufacturerDto getManufacturerDto() {
        return manufacturerDto;
    }

    public void setManufacturerDto(ManufacturerDto manufacturerDto) {
        this.manufacturerDto = manufacturerDto;
    }

    public List<SupplierDto> getSupplierDto() {
        return supplierDto;
    }

    public void setSupplierDto(List<SupplierDto> supplierDto) {
        this.supplierDto = supplierDto;
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

    public UnitDto getUnitDto() {
        return unitDto;
    }

    public void setUnitDto(UnitDto unitDto) {
        this.unitDto = unitDto;
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

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategoryDto) {
        this.productCategory = productCategoryDto;
    }
}