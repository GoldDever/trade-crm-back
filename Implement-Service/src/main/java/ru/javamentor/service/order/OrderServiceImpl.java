/*
package ru.javamentor.service.order;

import org.springframework.stereotype.Service;
import ru.javamentor.dto.order.OrderApproveDto;
import ru.javamentor.model.order.Order;
import ru.javamentor.model.order.OrderApprove;
import ru.javamentor.repository.order.OrderApproveRepository;
import ru.javamentor.repository.order.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderApproveRepository orderApproveRepository;

    public OrderServiceImpl(OrderRepository orderRepository, OrderApproveRepository orderApproveRepository) {
        this.orderRepository = orderRepository;
        this.orderApproveRepository = orderApproveRepository;
    }

    */
/**
     * Метод меняет флаг в Order на тот, что пришёл в orderApproveDto
     * сохраняет новый OrderApprove
     *
     * @param orderApproveDto - ДТО из которого получаем новый флаг isApprove
     * @param orderId - id по которому находим Order и изменяем у него флаг isApprove
     *//*

    @Override
    public void updateApproveStatus(OrderApproveDto orderApproveDto, Long orderId) {
        Order order = orderRepository.findById(orderId).get();
        OrderApprove orderApprove = new OrderApprove(orderApproveDto.isApprove(), orderApproveDto.getText(), order);

        order.setApprove(orderApproveDto.isApprove());
        orderRepository.save(order);
        orderApproveRepository.save(orderApprove);
    }
}
*/
