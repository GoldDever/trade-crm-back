package ru.javamentor.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.javamentor.model.order.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Query("UPDATE OrderItem oi SET oi.productCount = :productCount WHERE oi.id = :orderItemId")
    void setProductCountByOrderItem(@Param("orderItemId") Long orderItemId, @Param("productCount") Integer productCount);
}
