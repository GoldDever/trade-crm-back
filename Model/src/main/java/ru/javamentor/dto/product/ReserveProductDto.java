package ru.javamentor.dto.product;

import ru.javamentor.dto.order.OrderDto;

import java.time.LocalDateTime;


/**
 * DTO для передачи ReserveProduct на страницу
 */
public class ReserveProductDto {

    private Long id;
    private ProductDto product;
    private OrderDto order;
    private Integer productCount;
    private LocalDateTime createTime;


    public ReserveProductDto() {
    }

    public ReserveProductDto(
            ProductDto productDto,
            OrderDto order,
            Integer productCount) {
        this.product = productDto;
        this.order = order;
        this.productCount = productCount;
    }


    public ReserveProductDto(
            Long id,
            ProductDto productDto,
            OrderDto order,
            Integer productCount) {
        this.id = id;
        this.product = productDto;
        this.order = order;
        this.productCount = productCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }

    public OrderDto getOrder() {
        return order;
    }

    public void setOrder(OrderDto order) {
        this.order = order;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
