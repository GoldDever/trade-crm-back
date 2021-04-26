package ru.javamentor.model.product;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.javamentor.model.order.Order;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "reserve_products")
public class ReserveProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "product_count")
    private Integer productCount;

    @Column(name = "create_date_time")
    private LocalDateTime createDateTime = LocalDateTime.now();

    public ReserveProduct() {
    }

    public ReserveProduct(
            Product product,
            Order order,
            Integer productCount) {
        this.product = product;
        this.order = order;
        this.productCount = productCount;
        this.createDateTime = LocalDateTime.now();
    }

    public ReserveProduct(
            Product product,
            Order order,
            Long productCount) {
        this.product = product;
        this.order = order;
        this.productCount = Math.toIntExact(productCount);
        this.createDateTime = LocalDateTime.now();
    }

    public ReserveProduct(
            Long id,
            Product product,
            Order order,
            Integer productCount) {
        this.id = id;
        this.product = product;
        this.order = order;
        this.productCount = productCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }
}
