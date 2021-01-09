package ru.javamentor.service.order;

import org.springframework.stereotype.Service;
import ru.javamentor.dto.order.OrderApproveDto;
import ru.javamentor.model.order.Order;
import ru.javamentor.model.order.OrderApprove;
import ru.javamentor.repository.order.OrderApproveRepository;
import ru.javamentor.repository.order.OrderRepository;
import ru.javamentor.repository.product.ReserveProductRepository;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderApproveRepository orderApproveRepository;
    private final ReserveProductRepository reserveProductRepository;

    public OrderServiceImpl(OrderRepository orderRepository,
                            OrderApproveRepository orderApproveRepository,
                            ReserveProductRepository reserveProductRepository) {
        this.orderRepository = orderRepository;
        this.orderApproveRepository = orderApproveRepository;
        this.reserveProductRepository = reserveProductRepository;
    }

    /**
     * Метод меняет флаг в Order на тот, что пришёл в orderApproveDto
     * сохраняет новый OrderApprove
     *
     * @param orderApproveDto - ДТО из которого получаем новый флаг isApprove
     * @param orderId - id по которому находим Order и изменяем у него флаг isApprove
     */
    @Override
    public void updateApproveStatus(OrderApproveDto orderApproveDto, Long orderId) {
        Order order = orderRepository.findById(orderId).get();
        OrderApprove orderApprove = new OrderApprove(orderApproveDto.isApprove(), orderApproveDto.getText(), order);

        order.setApprove(orderApproveDto.isApprove());
        orderRepository.save(order);
        orderApproveRepository.save(orderApprove);
    }

    /**
     * Метод изменяет флаг на true.
     * И удаляет все резервы связанные с этим заказом.
     *
     * @param orderIdFromErp - идентификатор заказа из ERP системы
     */
    @Override
    public void updateShippedStatus(String orderIdFromErp) {
        Order order = orderRepository.findByIdFromErp(orderIdFromErp);
        order.setShipped(true);
        orderRepository.save(order);

        reserveProductRepository.deleteByOrderId(order.getId());
    }
}
