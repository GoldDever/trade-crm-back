package ru.javamentor.dto.product;

import ru.javamentor.model.product.ProductCategory;

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
    private UnitDto unitDto;
    private String productCategory;
    private BigDecimal price;


    public ProductDto(
            Long id,
            String productName,
            String madeCountry,
            ManufacturerDto manufacturerDto,
            List<SupplierDto> supplierDto,
            String article,
            UnitDto unitDto,
            String productCategory,
            BigDecimal price)
    {
        this.id = id;
        this.productName = productName;
        this.madeCountry = madeCountry;
        this.manufacturerDto = manufacturerDto;
        this.supplierDto = supplierDto;
        this.article = article;
        this.unitDto = unitDto;
        this.productCategory = productCategory;
        this.price = price;
    }

    public ProductDto(
            Long id,
            String productName,
            String madeCountry,
            String article) {
        this.id = id;
        this.productName = productName;
        this.madeCountry = madeCountry;
        this.article = article;
    }

    public ProductDto(String productName, String madeCountry,
                      ManufacturerDto manufacturerDto) {
        this.productName = productName;
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

    public UnitDto getUnitDto() {
        return unitDto;
    }

    public void setUnitDto(UnitDto unitDto) {
        this.unitDto = unitDto;
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
}