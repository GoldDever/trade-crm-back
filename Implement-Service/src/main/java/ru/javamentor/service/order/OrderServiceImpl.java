package ru.javamentor.service.order;

import org.springframework.stereotype.Service;
import ru.javamentor.dto.order.OrderApproveDto;
import ru.javamentor.model.order.Order;
import ru.javamentor.model.order.OrderApprove;
import ru.javamentor.model.user.User;
import ru.javamentor.repository.order.OrderApproveRepository;
import ru.javamentor.repository.order.OrderRepository;
import ru.javamentor.repository.product.ReserveProductRepository;
import ru.javamentor.repository.user.ClientRepository;
import ru.javamentor.repository.user.ManagerRepository;

import javax.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderApproveRepository orderApproveRepository;
    private final ClientRepository clientRepository;
    private final ManagerRepository managerRepository;
    private final ReserveProductRepository reserveProductRepository;

    public OrderServiceImpl(OrderRepository orderRepository,
                            OrderApproveRepository orderApproveRepository,
                            ClientRepository clientRepository,
                            ManagerRepository managerRepository,
                            ReserveProductRepository reserveProductRepository) {
        this.orderRepository = orderRepository;
        this.orderApproveRepository = orderApproveRepository;
        this.clientRepository = clientRepository;
        this.managerRepository = managerRepository;
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

    /**
     * Метод сохраняет новый Order
     *
     * @param clientId - id клиента
     * @param user - user из principal для получения manager
     */
    @Transactional
    @Override
    public void newOrder(Long clientId, User user) {
        Order order = new Order(
                clientRepository.findById(clientId).orElseThrow(),
                managerRepository.findById(user.getId()).orElseThrow()
        );
        orderRepository.save(order);
    }
}
