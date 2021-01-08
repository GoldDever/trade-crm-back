package ru.javamentor.service.order;

import ru.javamentor.dto.order.OrderItemDto;


public interface OrderItemService {
    void saveOrderItem(OrderItemDto orderItemDto, String orderId);
    void editOrderItem(Long orderId, Long orderItemId, Integer count);
}
