package ru.javamentor.dto.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO для передачи Order на страницу
 */
public class OrderDto {
    private Long id;
    private String idFromErp;
    private ClientDto client;
    private ManagerDto manager;
    private BigDecimal orderFullPrice;
    private Boolean approved;
    private Boolean paid;
    private Boolean shipped;
    private LocalDateTime createTime;

    public OrderDto() {
    }

    public OrderDto(Long id, String idFromErp, ClientDto client, ManagerDto manager,
                    BigDecimal orderFullPrice, Boolean approved, Boolean paid,
                    Boolean shipped, LocalDateTime createTime) {
        this.id = id;
        this.idFromErp = idFromErp;
        this.client = client;
        this.manager = manager;
        this.orderFullPrice = orderFullPrice;
        this.approved = approved;
        this.paid = paid;
        this.shipped = shipped;
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

    public ClientDto getClient() {
        return client;
    }

    public void setClient(ClientDto client) {
        this.client = client;
    }

    public ManagerDto getManager() {
        return manager;
    }

    public void setManager(ManagerDto manager) {
        this.manager = manager;
    }

    public BigDecimal getOrderFullPrice() {
        return orderFullPrice;
    }

    public void setOrderFullPrice(BigDecimal orderFullPrice) {
        this.orderFullPrice = orderFullPrice;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public Boolean getShipped() {
        return shipped;
    }

    public void setShipped(Boolean shipped) {
        this.shipped = shipped;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}