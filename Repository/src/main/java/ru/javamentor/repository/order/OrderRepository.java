package ru.javamentor.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.javamentor.model.order.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
