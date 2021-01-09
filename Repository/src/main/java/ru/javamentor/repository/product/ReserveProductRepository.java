package ru.javamentor.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.javamentor.model.product.ReserveProduct;

public interface ReserveProductRepository extends JpaRepository<ReserveProduct, Long> {
    void deleteByOrderId(Long id);

    @Query("SELECT rp.productCount FROM ReserveProduct rp " +
            "WHERE rp.product.id = :productId AND rp.order.id = :orderId")
    Integer getReserveProductCount(@Param("orderId") Long orderId, @Param("productId") Long productId);

    @Modifying
    @Query("DELETE FROM ReserveProduct rp " +
            "WHERE rp.product.id = :productId AND rp.order.id = :orderId")
    void deleteReserve(@Param("orderId") Long orderId, @Param("productId") Long productId);

    @Modifying
    @Query("UPDATE ReserveProduct rp SET rp.productCount=rp.productCount - :reserveProductCountEdit " +
            "WHERE rp.product.id = :productId AND rp.order.id = :orderId")
    void updateReserveProductCount(@Param("reserveProductCountEdit") Integer reserveProductCountEdit,
                                   @Param("orderId") Long orderId,
                                   @Param("productId") Long productId);
}
