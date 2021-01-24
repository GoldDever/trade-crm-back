package ru.javamentor.model.order;

import ru.javamentor.model.product.Product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "invoice_issued")
    private String invoiceIssued;

    @Column(name = "product_count")
    private Integer productCount;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private BigDecimal itemFullPrice;

    public OrderItem() {
    }

    public OrderItem(
            Long id,
            String invoiceIssued,
            Integer productCount,
            Product product,
            Order order
    ) {
        this.id = id;
        this.invoiceIssued = invoiceIssued;
        this.productCount = productCount;
        this.product = product;
        this.order = order;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getItemFullPrice() {
        return product.getPrice().multiply(BigDecimal.valueOf(productCount));
    }

    public void setItemFullPrice(BigDecimal itemFullPrice) {
        this.itemFullPrice = itemFullPrice;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
