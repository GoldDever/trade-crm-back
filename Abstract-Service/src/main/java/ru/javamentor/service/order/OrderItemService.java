package ru.javamentor.service.order;

import ru.javamentor.dto.order.OrderItemDto;
import ru.javamentor.model.order.OrderItem;



public interface OrderItemService {
    void saveOrderItem(OrderItemDto orderItemDto);

    void editOrderItemCount(Long orderItemId, Integer count);

    void deleteOrderItem(OrderItemDto orderItemDto);

    OrderItem getOrderItemByDTO(OrderItemDto orderItemDto);

    void updatePositions(Long orderId, Integer deletedPosition);

}
