package ru.javamentor.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.javamentor.model.order.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Query("update OrderItem c set c.productCount = :productCount WHERE c.id = :orderItemId")
    void setProductCountByOrderItem(@Param("orderItemId") Long orderItemId, @Param("productCount") Integer productCount);
}
