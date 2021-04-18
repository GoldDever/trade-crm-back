package ru.javamentor.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.javamentor.dto.order.OrderDto;
import ru.javamentor.model.order.Order;
import ru.javamentor.model.user.Client;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o.id FROM Order o " +
            "WHERE o.idFromErp = :orderIdFromErp")
    Long getOrderIdByIdFromErp(@Param("orderIdFromErp") String orderIdFromErp);

    @Modifying
    @Query("UPDATE Order o SET o.isShipped = true " +
            "WHERE o.id = :orderId")
    void updateOrderShippedStatus(@Param("orderId") Long orderId);

    Order findOrderById(Long orderId);

    @Query("SELECT new ru.javamentor.dto.order.OrderDto(" +
            "o.id, " +
            "o.idFromErp, " +
            "o.isApprove, " +
            "o.isPaid, " +
            "o.isShipped, " +
            "o.createTime) " +
            "FROM Order o " +
            "WHERE o.id = :orderId")
    OrderDto getOrderDtoWithOrderId(@Param("orderId") Long orderId);

    @Modifying
    @Query("UPDATE Order o SET o.client = :client WHERE o.id =:orderId")
    void updateOrderClient(@Param("orderId") Long orderId,@Param("client") Client client);

    @Query("SELECT o.client.id FROM Order o WHERE o.id = :orderId")
    Long getClientIdByOrderId(@Param("orderId") Long orderId);

    @Query("SELECT o.manager.id FROM Order o WHERE o.id = :orderId")
    Long getManagerIdByOrderId(@Param("orderId") Long orderId);

    @Query("SELECT new ru.javamentor.dto.order.OrderDto(" +
            "o.id, " +
            "o.idFromErp, " +
            "o.isApprove, " +
            "o.isPaid, " +
            "o.isShipped, " +
            "o.createTime) " +
            "FROM Order o " +
            "WHERE o.client.id = :clientId")
    List<OrderDto> getOrderDtoListWithClientId(Long clientId);

    @Modifying
    @Query("DELETE FROM Order oi WHERE oi.id = :orderId")
    void deleteOrderById(@Param("orderId") Long orderId);


    @Query("SELECT new ru.javamentor.dto.order.OrderDto(" +
            "o.id, " +
            "o.idFromErp, " +
            "o.isApprove, " +
            "o.isPaid, " +
            "o.isShipped, " +
            "o.createTime) " +
            "FROM Order o " +
            "WHERE o.manager.id = :managerId")
    List<OrderDto> getAllOrderDtoListByManagerId(@Param("managerId")Long managerId);
}
