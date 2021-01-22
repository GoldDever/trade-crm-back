package ru.javamentor.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.javamentor.dto.order.OrderDto;
import ru.javamentor.model.order.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o.id FROM Order o " +
            "WHERE o.idFromErp = :orderIdFromErp")
    Long getOrderIdByIdFromErp(@Param("orderIdFromErp") String orderIdFromErp);

    @Modifying
    @Query("UPDATE Order o SET o.isShipped = true " +
            "WHERE o.id = :orderId")
    void updateOrderShippedStatus(@Param("orderId") Long orderId);

    Order findOrderById(Long orderId);

    @Query("select new ru.javamentor.dto.order.OrderDto(o.id, o.idFromErp, o.orderFullPrice, o.isApprove, o.isPaid," +
            "o.isShipped, o.createTime) from Order o where o.id = :orderId")
    OrderDto getOrderDtoByOrderId(@Param("orderId") Long orderId);

    Long getOrderByIdAndClientAndId(@Param("orderId") Long orderId);

    Long getOrderByIdAndManagerAndId(@Param("orderId") Long orderId);
}
