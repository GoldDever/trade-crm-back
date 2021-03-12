package ru.javamentor.init.order;

import org.springframework.stereotype.Component;
import ru.javamentor.model.order.Order;
import ru.javamentor.model.order.OrderApprove;
import ru.javamentor.repository.order.OrderApproveRepository;
import ru.javamentor.repository.order.OrderRepository;

@Component
public class InitOrderApproveService {
    private final OrderApproveRepository orderApproveRepository;
    private final OrderRepository orderRepository;

    public InitOrderApproveService(OrderApproveRepository orderApproveRepository, OrderRepository orderRepository) {
        this.orderApproveRepository = orderApproveRepository;
        this.orderRepository = orderRepository;
    }

    public void initOrderApprove() {
        createOrderApprove(true, "approved", orderRepository.getOne(1L));
        createOrderApprove(true, "approved", orderRepository.getOne(2L));
    }

    private void createOrderApprove(boolean isApprove, String text, Order order) {
        OrderApprove orderApprove = new OrderApprove(isApprove, text, order);
        orderApproveRepository.save(orderApprove);
    }
}
