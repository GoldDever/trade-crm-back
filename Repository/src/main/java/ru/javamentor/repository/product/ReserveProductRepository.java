package ru.javamentor.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.javamentor.model.order.OrderItem;
import ru.javamentor.model.product.ReserveProduct;

import java.util.List;

public interface ReserveProductRepository extends JpaRepository<ReserveProduct, Long> {
    void deleteByOrderId(Long id);

    @Query("SELECT rp FROM ReserveProduct rp " + "WHERE rp.product.id = :productId AND rp.order.id = :orderId")
    List<ReserveProduct> getReserveProductList(@Param("orderId") Long orderId, @Param("productId") Long productId);

    @Query("SELECT rp.productCount FROM ReserveProduct rp " + "WHERE rp.product.id = :productId AND rp.order.id = :orderId")
    Integer getCountReservedProduct(@Param("orderId") Long orderId, @Param("productId") Long productId);

    @Query("SELECT SUM(rp.productCount) FROM ReserveProduct rp " +
            "WHERE rp.product.id = :productId AND rp.order.id = :orderId")
    Integer getSumOfReserveProductCounts(@Param("orderId") Long orderId, @Param("productId") Long productId);

    @Modifying
    @Query("DELETE FROM ReserveProduct rp " +
            "WHERE rp.id = :id AND rp.product.id = :productId AND rp.order.id = :orderId AND rp.productCount = :productCount")
    void deleteReserve(@Param("id") Long id,
                       @Param("orderId") Long orderId,
                       @Param("productId") Long productId,
                       @Param("productCount") Integer productCount);

    @Modifying
    @Query("UPDATE ReserveProduct rp SET rp.productCount=rp.productCount - :reserveProductCountEdit " +
            "WHERE rp.id = :id AND rp.product.id = :productId AND rp.order.id = :orderId AND rp.productCount = :count")
    void updateReserveProductCount(@Param("id") Long id,
                                   @Param("orderId") Long orderId,
                                   @Param("productId") Long productId,
                                   @Param("reserveProductCountEdit") Integer reserveProductCountEdit,
                                   @Param("count") Integer count);

    @Query("SELECT (p.productCount - SUM(rp.productCount)) FROM ReserveProduct rp, Product p " +
            "WHERE rp.product.id = :productId and p.id = :productId GROUP BY p.productCount")
    Integer countReserveProducts(@Param("productId") Long productId);

    Boolean existsByProductId(Long productId);

    @Query("SELECT oi FROM OrderItem oi "+ "WHERE oi.order.id = :orderId")
    List<OrderItem> getOrderItemListByOrderId(@Param("orderId") Long orderId);
}

