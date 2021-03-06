package ru.javamentor.dto.order;

import ru.javamentor.dto.product.ProductDto;

import java.math.BigDecimal;

/**
 * DTO для передачи OrderItem на страницу
 */
public class OrderItemDto {

    private Long id;
    private String invoiceIssued;
    private Integer productCount;
    private ProductDto product;
    private BigDecimal itemFullPrice = BigDecimal.ZERO;
    private Integer position;

    private BigDecimal currentMargePercent;

    public OrderItemDto() {
    }

    public OrderItemDto(
            Long id,
            String invoiceIssued,
            Integer productCount,
            ProductDto product,
            Integer position,
            BigDecimal itemFullPrice
    ) {
        this.id = id;
        this.invoiceIssued = invoiceIssued;
        this.productCount = productCount;
        this.product = product;
        this.position = position;
        this.itemFullPrice = itemFullPrice;
    }

    public OrderItemDto(
            Long id,
            String invoiceIssued,
            Integer productCount,
            Integer position,
            BigDecimal currentMargePercent
    ) {
        this.id = id;
        this.invoiceIssued = invoiceIssued;
        this.productCount = productCount;
        this.position = position;
        this.currentMargePercent = currentMargePercent;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInvoiceIssued() {
        return invoiceIssued;
    }

    public void setInvoiceIssued(String invoiceIssued) {
        this.invoiceIssued = invoiceIssued;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }

    public BigDecimal getItemFullPrice() {
        BigDecimal margin = (currentMargePercent == null) ? product.getStandardMargin() : currentMargePercent;

        itemFullPrice = product.getPrice()
                .add(product.getPrice().multiply(margin).divide(BigDecimal.valueOf(100)))
                .multiply(BigDecimal.valueOf(productCount)).setScale(2);
        return itemFullPrice;
    }

    public void setItemFullPrice(BigDecimal itemFullPrice) {
        this.itemFullPrice = itemFullPrice;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }


    public BigDecimal getCurrentMargePercent() {
        return currentMargePercent;
    }

    public void setCurrentMargePercent(BigDecimal currentMargePercent) {
        this.currentMargePercent = currentMargePercent;
    }
}
