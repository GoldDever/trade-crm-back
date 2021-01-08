package ru.javamentor.init;

import org.springframework.beans.factory.annotation.Autowired;
import ru.javamentor.model.order.Order;
import ru.javamentor.repository.order.OrderRepository;

public class InitService {

    @Autowired
    private OrderRepository orderRepository;

    private void init() {
        initOrder();
    }


    private void initOrder() {
        orderRepository.save(new Order());
    }
}
