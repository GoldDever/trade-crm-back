package ru.javamentor.service.order;

import ru.javamentor.dto.order.OrderItemDto;


public interface OrderItemService {
    void saveOrderItem(OrderItemDto orderItemDto, String orderId);
}
