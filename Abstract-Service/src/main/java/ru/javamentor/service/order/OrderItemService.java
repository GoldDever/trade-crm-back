package ru.javamentor.service.order;

import ru.javamentor.dto.order.OrderItemDto;
import ru.javamentor.model.order.OrderItem;


public interface OrderItemService {
    void saveOrderItem(Long orderId, OrderItemDto orderItemDto);

    void editOrderItem(Long orderItemId, Integer count);

    void deleteOrderItem(Long orderItemId);

    OrderItem getOrderItemByDTO(OrderItemDto orderItemDto);

    void updatePositions(Long orderId, Integer deletedPosition);

    void updateOrderItem(OrderItemDto orderItemDto);

    void editProductPrice(Long orderItemId, Double newPrice);

    boolean isExistsByOrderItemId(Long orderItemId);

}
