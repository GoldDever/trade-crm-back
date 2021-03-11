package ru.javamentor.init.order;

import org.springframework.stereotype.Component;
import ru.javamentor.model.order.Order;
import ru.javamentor.model.user.Client;
import ru.javamentor.model.user.Manager;
import ru.javamentor.repository.order.OrderRepository;
import ru.javamentor.repository.user.ClientRepository;
import ru.javamentor.repository.user.ManagerRepository;

import java.time.LocalDateTime;
@Component
public class InitOrderService {

    private final ClientRepository clientRepository;
    private final ManagerRepository managerRepository;
    private final OrderRepository orderRepository;


    public InitOrderService(ClientRepository clientRepository, ManagerRepository managerRepository, OrderRepository orderRepository) {
        this.clientRepository = clientRepository;
        this.managerRepository = managerRepository;
        this.orderRepository = orderRepository;
    }

    public void initOrder() {
        Client client1 = clientRepository.findById(9L).get();
        Manager manager1 = managerRepository.findByUsername("alexey@mail.ru");
        Order order = new Order();
        order.setIdFromErp("idFromErp");
        order.setClient(client1);
        order.setManager(manager1);
        order.setApprove(true);
        order.setPaid(true);
        order.setShipped(true);
        order.setCreateTime(LocalDateTime.parse("2020-12-30T11:03:12"));
        orderRepository.save(order);

        Client client2 = clientRepository.findById(8L).get();
        Order order2 = new Order();
        order2.setIdFromErp("idFromErp");
        order2.setClient(client2);
        order2.setManager(manager1);
        order2.setApprove(true);
        order2.setPaid(true);
        order2.setShipped(true);
        order2.setCreateTime(LocalDateTime.parse("2020-11-30T11:03:12"));
        orderRepository.save(order2);
    }
}
