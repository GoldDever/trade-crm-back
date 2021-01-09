package ru.javamentor.model.order;

import ru.javamentor.model.user.Client;
import ru.javamentor.model.user.Manager;

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
import java.math.RoundingMode;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private Manager manager;

    @Column(name = "order_full_price")
    private BigDecimal orderFullPrice;

    @Column(name = "is_approve")
    private Boolean isApprove;

    @Column(name = "is_paid")
    private Boolean isPaid;

    @Column(name = "is_shipped")
    private Boolean isShipped;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    public Order() {
    }

    public Order(
            Long id,
            String idFromErp,
            Client client,
            Manager manager,
            BigDecimal orderFullPrice,
            Boolean isApprove,
            Boolean isPaid,
            Boolean isShipped,
            LocalDateTime createTime) {
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
        this.orderFullPrice = orderFullPrice.setScale(2, RoundingMode.HALF_UP);
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
