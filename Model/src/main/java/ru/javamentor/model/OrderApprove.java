package ru.javamentor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_approve")
public class OrderApprove {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "is_approve")
    private boolean isApprove;

    @Column(name = "text")
    private String text;

//    @ManyToMany(targetEntity = Order.class, cascade = { CascadeType.ALL }, fetch = FetchType.LAZY )
//    @JoinTable(
//            name = "order_approves",
//            joinColumns = { @JoinColumn(name = "order_approve_id") },
//            inverseJoinColumns = { @JoinColumn(name = "orders_id") }
//    )
    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public OrderApprove() {
    }

    public OrderApprove(boolean isApprove, String text, Order order) {
        this.isApprove = isApprove;
        this.text = text;
        this.order = order;
    }

    public OrderApprove(Long id, boolean isApprove, String text, Order order) {
        this.id = id;
        this.isApprove = isApprove;
        this.text = text;
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isApprove() {
        return isApprove;
    }

    public void setApprove(boolean approve) {
        isApprove = approve;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
