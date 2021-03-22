package ru.javamentor.dto.order;

public class OrderApproveAnswerDto {
    private boolean isApprove;
    private String text;
    private OrderApproveRequestDto orderApproveRequest;

    public OrderApproveAnswerDto(boolean isApprove, String text, OrderApproveRequestDto orderApproveRequest) {
        this.isApprove = isApprove;
        this.text = text;
        this.orderApproveRequest = orderApproveRequest;
    }

    public OrderApproveAnswerDto() {
    }

    public boolean isApprove() {
        return isApprove;
    }

    public void setIsApprove(boolean approve) {
        isApprove = approve;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public OrderApproveRequestDto getOrderApproveRequest() {
        return orderApproveRequest;
    }

    public void setOrderApproveRequest(OrderApproveRequestDto orderApproveRequest) {
        this.orderApproveRequest = orderApproveRequest;
    }
}
