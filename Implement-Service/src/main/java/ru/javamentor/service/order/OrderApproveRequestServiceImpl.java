package ru.javamentor.service.order;

import org.springframework.stereotype.Service;
import ru.javamentor.dto.order.OrderApproveRequestDto;
import ru.javamentor.model.order.Order;
import ru.javamentor.model.order.OrderApproveRequest;
import ru.javamentor.repository.order.OrderApproveRequestRepository;
import ru.javamentor.repository.order.OrderRepository;

@Service
public class OrderApproveRequestServiceImpl implements OrderApproveRequestService {
    private final OrderApproveRequestRepository orderApproveRequestRepository;
    private final OrderRepository orderRepository;

    public OrderApproveRequestServiceImpl(OrderApproveRequestRepository orderApproveRequestRepository, OrderRepository orderRepository) {
        this.orderApproveRequestRepository = orderApproveRequestRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public void saveOrderApproveRequest(OrderApproveRequestDto orderApproveRequestDto) {
        Order order = orderRepository.findById(orderApproveRequestDto.getOrderId()).orElseThrow();
        OrderApproveRequest orderApproveRequest = new OrderApproveRequest(orderApproveRequestDto.getText(), order);
        orderApproveRequestRepository.save(orderApproveRequest);
    }
}
