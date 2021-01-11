package ru.javamentor.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.javamentor.model.product.ReserveProduct;

import java.util.List;

public interface ReserveProductRepository extends JpaRepository<ReserveProduct, Long> {
    void deleteByOrderId(Long id);

    @Query("SELECT rp.productCount FROM ReserveProduct rp " +
            "WHERE rp.product.id = :productId AND rp.order.id = :orderId")
    List<Integer> getReserveProductCount(@Param("orderId") Long orderId, @Param("productId") Long productId);

    @Modifying
    @Query("DELETE FROM ReserveProduct rp " +
            "WHERE rp.product.id = :productId AND rp.order.id = :orderId AND rp.productCount = :productCount")
    void deleteReserve(@Param("orderId") Long orderId,
                       @Param("productId") Long productId,
                       @Param("productCount") Integer productCount);

    @Modifying
    @Query("UPDATE ReserveProduct rp SET rp.productCount=rp.productCount - :reserveProductCountEdit " +
            "WHERE rp.product.id = :productId AND rp.order.id = :orderId AND rp.productCount = :count")
    void updateReserveProductCount(@Param("orderId") Long orderId,
                                   @Param("productId") Long productId,
                                   @Param("reserveProductCountEdit") Integer reserveProductCountEdit,
                                   @Param("count") Integer count);
}
