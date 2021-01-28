package ru.javamentor.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.javamentor.dto.order.OrderItemDto;
import ru.javamentor.model.order.OrderItem;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Query("UPDATE OrderItem oi SET oi.productCount = :productCount WHERE oi.id = :orderItemId")
    void setProductCountByOrderItem(@Param("orderItemId") Long orderItemId, @Param("productCount") Integer productCount);

    @Query("SELECT oi.product.id " +
            "FROM OrderItem oi " +
            "WHERE oi.id = :orderItemId")
    Long findProductIdByOrderItemId(@Param("orderItemId") Long orderItemId);

    @Query("SELECT new ru.javamentor.dto.order.OrderItemDto(" +
            "oi.id, " +
            "oi.invoiceIssued," +
            "oi.productCount, " +
            "oi.itemFullPrice) " +
            "FROM OrderItem oi " +
            "JOIN oi.order o " +
            "WHERE o.id = :orderId")
    List<OrderItemDto> getListOrderItemDtoByOrderId(@Param("orderId")Long orderId);


}
