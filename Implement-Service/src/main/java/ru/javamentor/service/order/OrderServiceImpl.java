package ru.javamentor.service.order;

import org.springframework.stereotype.Service;
import ru.javamentor.dto.order.OrderApproveDto;
import ru.javamentor.model.order.Order;
import ru.javamentor.model.order.OrderApprove;
import ru.javamentor.model.user.User;
import ru.javamentor.repository.order.OrderApproveRepository;
import ru.javamentor.repository.order.OrderRepository;
import ru.javamentor.repository.user.ClientRepository;
import ru.javamentor.repository.user.ManagerRepository;

import javax.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderApproveRepository orderApproveRepository;
    private final ClientRepository clientRepository;
    private final ManagerRepository managerRepository;

    public OrderServiceImpl(OrderRepository orderRepository,
                            OrderApproveRepository orderApproveRepository,
                            ClientRepository clientRepository,
                            ManagerRepository managerRepository) {
        this.orderRepository = orderRepository;
        this.orderApproveRepository = orderApproveRepository;
        this.clientRepository = clientRepository;
        this.managerRepository = managerRepository;
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
