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
    private Long orderId;

    public OrderItemDto() {
    }

    public OrderItemDto( //конструктор для новых записей
                         String invoiceIssued,
                         Integer productCount,
                         ProductDto product,
                         BigDecimal itemFullPrice,
                         Integer position,
                         Long orderId
    ) {
        this.invoiceIssued = invoiceIssued;
        this.productCount = productCount;
        this.product = product;
        this.itemFullPrice = itemFullPrice;
        this.position = position;
        this.orderId = orderId;
    }

    public OrderItemDto(
            Long id,
            String invoiceIssued,
            Integer productCount,
            ProductDto product,
            BigDecimal itemFullPrice,
            Integer position,
            Long orderId
    ) {
        this.id = id;
        this.invoiceIssued = invoiceIssued;
        this.productCount = productCount;
        this.product = product;
        this.itemFullPrice = itemFullPrice;
        this.position = position;
        this.orderId = orderId;
    }

    public OrderItemDto(
            Long id,
            String invoiceIssued,
            Integer productCount,
            Integer position
    ) {
        this.id = id;
        this.invoiceIssued = invoiceIssued;
        this.productCount = productCount;
        this.position = position;
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

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
