package ru.javamentor.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javamentor.model.order.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByIdFromErp(String idFromErp);
}
