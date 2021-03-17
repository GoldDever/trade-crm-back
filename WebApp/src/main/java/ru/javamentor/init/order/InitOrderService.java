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

        Manager manager = managerRepository.findByUsername("alexey@mail.ru");
        Client client1 = clientRepository.findByUsername("artem@mail.com");
        createOrder(manager, client1, "2020-12-30T11:03:12");

        Client client2 = clientRepository.findByUsername("ivanov@mail.ru");
        createOrder(manager, client2, "2020-11-30T11:03:12");

        createOrder(manager, client1, "2020-11-30T11:03:12");
    }

    private void createOrder(Manager manager, Client client, String dateTime) {
        Order order = new Order();
        order.setClient(client);
        order.setManager(manager);
        order.setApprove(true);
        order.setPaid(true);
        order.setShipped(true);
        order.setCreateTime(LocalDateTime.parse(dateTime));
        orderRepository.save(order);
    }
}
