package ru.javamentor.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javamentor.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
