package ru.javamentor.model.order;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.javamentor.model.user.Client;
import ru.javamentor.model.user.Manager;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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

    public Order(Client client,
                 Manager manager) {
        this.idFromErp = null;
        this.client = client;
        this.manager = manager;
        this.isApprove = false;
        this.isPaid = false;
        this.isShipped = false;
        this.createTime = LocalDateTime.now();
    }

    public Order(
            Long id,
            String idFromErp,
            Client client,
            Manager manager,
            Boolean isApprove,
            Boolean isPaid,
            Boolean isShipped,
            LocalDateTime createTime) {
        this.id = id;
        this.idFromErp = idFromErp;
        this.client = client;
        this.manager = manager;
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
