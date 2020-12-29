package ru.javamentor.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javamentor.model.order.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
