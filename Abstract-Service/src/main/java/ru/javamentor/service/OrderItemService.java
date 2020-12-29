package ru.javamentor.service;

import ru.javamentor.dto.order.OrderItemDto;


public interface OrderItemService {
    void saveOrderItem(OrderItemDto orderItemDto, String orderId);
}
