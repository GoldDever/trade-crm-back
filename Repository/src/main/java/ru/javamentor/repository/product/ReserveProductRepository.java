package ru.javamentor.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.javamentor.model.product.ReserveProduct;

public interface ReserveProductRepository extends JpaRepository<ReserveProduct, Long> {
    void deleteByOrderId(Long id);

    @Query("SELECT rp.productCount FROM ReserveProduct rp " +
            "WHERE rp.product = :productId AND rp.order = :orderId")
    Integer getReserveProductCount(@Param("orderId") Long orderId, @Param("productId") Long productId);

    @Query("DELETE FROM ReserveProduct rp " +
            "WHERE rp.product = :productId AND rp.order = :orderId")
    void deleteReserve(Long orderId, Long productId);

    @Query("UPDATE ReserveProduct rp SET rp.productCount=rp.productCount - :reserveProductCountEdit " +
            "WHERE rp.product = :productId AND rp.order = :orderId")
    void updateReserveProductCount(@Param("reserveProductCountEdit") Integer reserveProductCountEdit,
                                   @Param("orderId") Long orderId,
                                   @Param("productId") Long productId);
}
