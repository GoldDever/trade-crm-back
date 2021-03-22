package ru.javamentor.init.order;

import org.springframework.stereotype.Component;
import ru.javamentor.model.order.Order;
import ru.javamentor.model.order.OrderApproveRequest;
import ru.javamentor.repository.order.OrderApproveRequestRepository;
import ru.javamentor.repository.order.OrderRepository;

@Component
public class InitOrderApproveService {
    private final OrderApproveRequestRepository orderApproveRequestRepository;
    private final OrderRepository orderRepository;

    public InitOrderApproveService(OrderApproveRequestRepository orderApproveRequestRepository, OrderRepository orderRepository) {
        this.orderApproveRequestRepository = orderApproveRequestRepository;
        this.orderRepository = orderRepository;
    }

    public void initOrderApprove() {
        createOrderApprove("Need to be approved", orderRepository.getOne(1L));
    }

    private void createOrderApprove(String text, Order order) {
        OrderApproveRequest orderApproveRequest = new OrderApproveRequest(text, order);
        orderApproveRequestRepository.save(orderApproveRequest);
    }
}
