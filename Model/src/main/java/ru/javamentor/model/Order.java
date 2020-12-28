package ru.javamentor.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "id_from_erp")
    private String idFromErp;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private Manager manager;

    @Column(name = "order_full_price")
    private BigDecimal orderFullPrice;

    @Column(name = "is_approve")
    private Boolean isApprove;

    @Column(name = "paid")
    private Boolean isPaid;

    @Column(name = "shipped")
    private Boolean isShipped;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    public Order() {
    }

    public Order(Long id, String idFromErp, Client client, Manager manager, BigDecimal orderFullPrice, Boolean isApprove, Boolean isPaid, Boolean isShipped, LocalDateTime createTime) {
        this.id = id;
        this.idFromErp = idFromErp;
        this.client = client;
        this.manager = manager;
        this.orderFullPrice = orderFullPrice;
        this.isApprove = isApprove;
        this.isPaid = isPaid;
        this.isShipped = isShipped;
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdFromErp() {
        return idFromErp;
    }

    public void setIdFromErp(String idFromErp) {
        this.idFromErp = idFromErp;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public BigDecimal getOrderFullPrice() {
        return orderFullPrice;
    }

    public void setOrderFullPrice(BigDecimal orderFullPrice) {
        this.orderFullPrice = orderFullPrice;
    }

    public Boolean getApprove() {
        return isApprove;
    }

    public void setApprove(Boolean approve) {
        isApprove = approve;
    }

    public Boolean getPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }

    public Boolean getShipped() {
        return isShipped;
    }

    public void setShipped(Boolean shipped) {
        isShipped = shipped;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
