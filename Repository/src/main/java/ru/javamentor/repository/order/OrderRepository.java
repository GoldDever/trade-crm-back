package ru.javamentor.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.javamentor.model.order.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o.id FROM Order o " +
            "WHERE o.idFromErp = :orderIdFromErp")
    Long getOrderIdByIdFromErp(@Param("orderIdFromErp") String orderIdFromErp);

    @Modifying
    @Query("UPDATE Order o SET o.isShipped = true " +
            "WHERE o.id = :orderId")
    void updateOrderShippedStatus(@Param("orderId") Long orderId);
}
