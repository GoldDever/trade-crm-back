package ru.javamentor.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javamentor.model.order.OrderItem;
import ru.javamentor.model.product.ReserveProduct;

import java.util.List;

public interface ReserveProductRepository extends JpaRepository<ReserveProduct, Long> {
    void deleteByOrderId(Long id);

    @Query("SELECT rp FROM ReserveProduct rp " + "WHERE rp.product.id = :productId AND rp.order.id = :orderId")
    List<ReserveProduct> getReserveProductList(@Param("orderId") Long orderId, @Param("productId") Long productId);

    @Query("SELECT new ru.javamentor.model.product.ReserveProduct(rp.product, rp.order, SUM(rp.productCount)) " +
            "FROM ReserveProduct rp WHERE  rp.order.id = :orderId GROUP BY rp.product, rp.order ORDER BY rp.product.id")
    List<ReserveProduct> getReserveProductByOrder(@Param("orderId") Long orderId);

    @Query("SELECT rp.productCount FROM ReserveProduct rp " + "WHERE rp.product.id = :productId AND rp.order.id = :orderId")
    Integer getCountReservedProduct(@Param("orderId") Long orderId, @Param("productId") Long productId);

    @Query("SELECT COALESCE(SUM(rp.productCount),0) FROM ReserveProduct rp " +
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

    @Query("SELECT p.productCount - COALESCE(SUM(rp.productCount),0) FROM  Product p LEFT JOIN ReserveProduct rp ON p.id = rp.product.id " +
            "WHERE p.id = :productId GROUP BY p.productCount")
    Integer countReserveProducts(@Param("productId") Long productId);

    Boolean existsByProductId(Long productId);

    @Query("SELECT oi FROM OrderItem oi " + "WHERE oi.order.id = :orderId")
    List<OrderItem> getOrderItemListByOrderId(@Param("orderId") Long orderId);

    @Query("SELECT rp FROM ReserveProduct rp WHERE rp.product.id = :productId")
    List<ReserveProduct> getReserveProductListByProductId(@Param("productId") Long productId);
    @Transactional
    @Modifying
    @Query("DELETE FROM ReserveProduct rp " +
            "WHERE rp.order.id = :orderId")
    void deleteReserveByOrderId(Long orderId);
}
