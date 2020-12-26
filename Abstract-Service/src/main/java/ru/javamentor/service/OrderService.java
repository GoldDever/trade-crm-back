package ru.javamentor.service;

import ru.javamentor.model.Order;

public interface OrderService {
    Order getOrderById(Long id);

    void saveOrder(Order order);
}
