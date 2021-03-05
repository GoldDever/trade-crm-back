package ru.javamentor.service.order;

import ru.javamentor.dto.order.OrderItemDto;
import ru.javamentor.model.order.OrderItem;


public interface OrderItemService {
    void saveOrderItem(OrderItemDto orderItemDto, String orderId);
    void editOrderItem(Long orderItemId, Integer count);
    void deleteOrderItem(Long orderItemId);
    OrderItem getOrderItemByDTO(OrderItemDto orderItemDto);
}
