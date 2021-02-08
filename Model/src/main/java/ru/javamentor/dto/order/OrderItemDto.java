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

    public OrderItemDto() {
    }

    public OrderItemDto(
            Long id,
            String invoiceIssued,
            Integer productCount,
            ProductDto product,
            BigDecimal itemFullPrice
    ) {
        this.id = id;
        this.invoiceIssued = invoiceIssued;
        this.productCount = productCount;
        this.product = product;
        this.itemFullPrice = itemFullPrice;
    }

    public OrderItemDto(
            Long id,
            String invoiceIssued,
            Integer productCount
    ) {
        this.id = id;
        this.invoiceIssued = invoiceIssued;
        this.productCount = productCount;
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
        itemFullPrice = product.getPrice()
                .multiply(BigDecimal.valueOf(productCount));
        return itemFullPrice;
    }

    public void setItemFullPrice(BigDecimal itemFullPrice) {
        this.itemFullPrice = itemFullPrice;
    }
}
