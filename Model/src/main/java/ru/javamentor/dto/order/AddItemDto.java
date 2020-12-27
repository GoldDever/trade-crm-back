package ru.javamentor.dto.order;

public class AddItemDto {
    private OrderDto orderDto;
    private OrderItemDto orderItemDto;

    public AddItemDto() {
    }

    public AddItemDto(OrderDto orderDto, OrderItemDto orderItemDto) {
        this.orderDto = orderDto;
        this.orderItemDto = orderItemDto;
    }

    public OrderDto getOrderDto() {
        return orderDto;
    }

    public void setOrderDto(OrderDto orderDto) {
        this.orderDto = orderDto;
    }

    public OrderItemDto getOrderItemDto() {
        return orderItemDto;
    }

    public void setOrderItemDto(OrderItemDto orderItemDto) {
        this.orderItemDto = orderItemDto;
    }
}
