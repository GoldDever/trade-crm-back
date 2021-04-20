package ru.javamentor.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.javamentor.dto.order.OrderItemDto;
import ru.javamentor.model.order.OrderItem;
import ru.javamentor.model.product.ReserveProduct;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Modifying
    @Query("UPDATE OrderItem oi SET oi.productCount = :productCount WHERE oi.id = :orderItemId")
    void setProductCountByOrderItem(@Param("orderItemId") Long orderItemId, @Param("productCount") Integer productCount);

    @Query("SELECT oi.product.id " +
            "FROM OrderItem oi " +
            "WHERE oi.id = :orderItemId")
    Long findProductIdByOrderItemId(@Param("orderItemId") Long orderItemId);

    @Query("SELECT new ru.javamentor.dto.order.OrderItemDto(" +
            "oi.id, " +
            "oi.invoiceIssued, " +
            "oi.productCount, " +
            "oi.position, " +
            "oi.currentMargePercent) " +
            "FROM OrderItem oi " +
            "JOIN oi.order o " +
            "WHERE o.id = :orderId " +
            "ORDER BY oi.position")
    List<OrderItemDto> getListOrderItemDtoByOrderId(@Param("orderId") Long orderId);

    @Modifying
    @Query("DELETE FROM OrderItem oi WHERE oi.id = :orderItemId")
    void deleteOrderItemById(@Param("orderItemId") Long orderItemId);

    @Query("SELECT oi FROM OrderItem oi WHERE oi.id = :orderItemId")
    OrderItem getOrderItemByDtoID(@Param("orderItemId") Long orderItemId);


    @Query("SELECT oi " +
            "FROM OrderItem oi " +
            "JOIN oi.order o " +
            "WHERE o.id = :orderId " +
            "ORDER BY oi.position")
    List<OrderItem> getListOrderItemByOrderId(@Param("orderId") Long orderId);

    @Modifying
    @Query("UPDATE OrderItem oi SET oi.position = :position WHERE oi.id = :orderItemId")
    void updateOrderItemPosition(@Param("orderItemId") Long orderItemId, @Param("position") Integer position);

    @Query("SELECT COUNT(oi) " +
            "FROM OrderItem oi " +
            "JOIN oi.order o " +
            "WHERE o.id = :orderId ")
    Integer getNumberOfPositionInOrder(@Param("orderId") Long orderId);

    @Query("SELECT new ru.javamentor.model.product.ReserveProduct(oi.product, oi.order, SUM(oi.productCount)) " +
            "FROM OrderItem oi WHERE  oi.order.id = :orderId GROUP BY oi.product, oi.order  ORDER BY oi.product.id")
    List<ReserveProduct> getFutureReserveProductByOrder(@Param("orderId") Long orderId);

    @Query("SELECT orderItem.order.id FROM OrderItem orderItem WHERE orderItem.id = :orderItemId")
    Long getOrderId(@Param("orderItemId") Long orderItemId);
}
