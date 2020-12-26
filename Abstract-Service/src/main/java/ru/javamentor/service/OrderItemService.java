package ru.javamentor.service;

import ru.javamentor.model.OrderItem;

import java.util.Optional;

public interface OrderItemService {
    OrderItem getOrderItemById(Long id);

    void saveOrderItem(OrderItem orderItem);
}
