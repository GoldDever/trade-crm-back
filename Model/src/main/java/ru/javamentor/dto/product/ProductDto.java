package ru.javamentor.dto.product;

import java.math.BigDecimal;
import java.util.List;

/**
 * DTO для передачи на страницу продукта
 */
public class ProductDto {
    private Long id;
    private String productName;
    private String madeCountry;
    private ManufacturerDto manufacturerDto;
    private List<SupplierDto> supplierDto;
    private String article;
    private UnitDto unit;
    private String productCategory;
    private BigDecimal price;
    private BigDecimal minMargin;
    private BigDecimal standardMargin;


    public ProductDto(
            Long id,
            String productName,
            String madeCountry,
            ManufacturerDto manufacturerDto,
            List<SupplierDto> supplierDto,
            String article,
            UnitDto unit,
            String productCategory,
            BigDecimal price,
            BigDecimal minMargin,
            BigDecimal standardMargin)
    {
        this.id = id;
        this.productName = productName;
        this.madeCountry = madeCountry;
        this.manufacturerDto = manufacturerDto;
        this.supplierDto = supplierDto;
        this.article = article;
        this.unit = unit;
        this.productCategory = productCategory;
        this.price = price;
        this.minMargin = minMargin;
        this.standardMargin = standardMargin;
    }

    public ProductDto(
            Long id,
            String productName,
            String madeCountry,
            String article,
            BigDecimal price
    ) {
        this.id = id;
        this.productName = productName;
        this.madeCountry = madeCountry;
        this.article = article;
        this.price = price;
    }

    public ProductDto(
            Long id,
            String productName,
            String madeCountry,
            String article,
            BigDecimal price,
            BigDecimal minMargin,
            BigDecimal standardMargin
    ) {
        this.id = id;
        this.productName = productName;
        this.madeCountry = madeCountry;
        this.article = article;
        this.price = price;
        this.minMargin = minMargin;
        this.standardMargin = standardMargin;
    }


    public ProductDto(String productName, String madeCountry,
                      ManufacturerDto manufacturerDto) {
        this.productName = productName;
    }

    public ProductDto() {
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



    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public UnitDto getUnit() { return unit; }

    public void setUnit(UnitDto unit) {
        this.unit = unit;
    }

    public BigDecimal getMinMargin() {
        return minMargin;
    }

    public void setMinMargin(BigDecimal minMargin) {
        this.minMargin = minMargin;
    }

    public BigDecimal getStandardMargin() {
        return standardMargin;
    }

    public void setStandardMargin(BigDecimal standardMargin) {
        this.standardMargin = standardMargin;
    }
}