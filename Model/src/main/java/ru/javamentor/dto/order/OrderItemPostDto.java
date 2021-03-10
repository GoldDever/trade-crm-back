package ru.javamentor.dto.order;

import ru.javamentor.dto.product.ProductDto;

import java.math.BigDecimal;

public class OrderItemPostDto {

    private String invoiceIssued;
    private Integer productCount;
    private Long orderId;
    private ProductDto product;
    private BigDecimal itemFullPrice;
    private BigDecimal currentMargePercent;

    public OrderItemPostDto(String invoiceIssued, Integer productCount, Long orderId, ProductDto product, BigDecimal itemFullPrice, BigDecimal currentMargePercent) {
        this.invoiceIssued = invoiceIssued;
        this.productCount = productCount;
        this.orderId = orderId;
        this.product = product;
        this.itemFullPrice = itemFullPrice;
        this.currentMargePercent = currentMargePercent;
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

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }

    public BigDecimal getItemFullPrice() {
        return itemFullPrice;
    }

    public void setItemFullPrice(BigDecimal itemFullPrice) {
        this.itemFullPrice = itemFullPrice;
    }

    public BigDecimal getCurrentMargePercent() {
        return currentMargePercent;
    }

    public void setCurrentMargePercent(BigDecimal currentMargePercent) {
        this.currentMargePercent = currentMargePercent;
    }
}
