package ru.javamentor.dto.order;

public class OrderApproveDto {
    private String text;
    private boolean approve;
    private OrderDto orderDto;

    public OrderApproveDto() {
    }

    public OrderApproveDto(String text, boolean approve, OrderDto orderDto) {
        this.text = text;
        this.approve = approve;
        this.orderDto = orderDto;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isApprove() {
        return approve;
    }

    public void setApprove(boolean approve) {
        this.approve = approve;
    }

    public OrderDto getOrderDto() {
        return orderDto;
    }

    public void setOrderDto(OrderDto orderDto) {
        this.orderDto = orderDto;
    }
}
