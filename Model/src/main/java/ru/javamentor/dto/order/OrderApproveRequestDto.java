package ru.javamentor.dto.order;

public class OrderApproveRequestDto {
    private Long id;
    private String text;
    private Long orderId;

    public OrderApproveRequestDto() {
    }

    public OrderApproveRequestDto(String text, Long orderId) {
        this.text = text;
        this.orderId = orderId;
    }

    public OrderApproveRequestDto(Long id, String text, Long orderId) {
        this.id = id;
        this.text = text;
        this.orderId = orderId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
