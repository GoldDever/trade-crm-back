package ru.javamentor.model.product;

import ru.javamentor.model.order.Order;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
<<<<<<< HEAD
=======
import javax.persistence.ManyToOne;
>>>>>>> origin/dev
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
<<<<<<< HEAD
@Table(name = "reserve_products")
public class ReserveProduct {

=======
@Table(name = "reserve_product")
public class ReserveProduct {
>>>>>>> origin/dev
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

<<<<<<< HEAD
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToOne
=======
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
>>>>>>> origin/dev
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "product_count")
    private Integer productCount;

    public ReserveProduct() {
    }

<<<<<<< HEAD
    public ReserveProduct(Long id, Product product, Order order, Integer productCount) {
=======
    public ReserveProduct(
            Product product,
            Order order,
            Integer productCount) {
        this.product = product;
        this.order = order;
        this.productCount = productCount;
    }

    public ReserveProduct(
            Long id,
            Product product,
            Order order,
            Integer productCount) {
>>>>>>> origin/dev
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

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }
}
