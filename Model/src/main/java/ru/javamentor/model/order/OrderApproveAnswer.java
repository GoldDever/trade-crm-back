package ru.javamentor.model.order;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_approve_answers")
public class OrderApproveAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "approve")
    private boolean isApprove;

    @Column(name = "description")
    private String text;


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "request_id")
    private OrderApproveRequest orderApproveRequest;

    public OrderApproveAnswer() {
    }

    public OrderApproveAnswer(Long id, boolean isApprove, String text, OrderApproveRequest orderApproveRequest) {
        this.id = id;
        this.isApprove = isApprove;
        this.text = text;
        this.orderApproveRequest = orderApproveRequest;
    }

    public OrderApproveAnswer(boolean isApprove, String text, OrderApproveRequest orderApproveRequest) {
        this.isApprove = isApprove;
        this.text = text;
        this.orderApproveRequest = orderApproveRequest;
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

}
