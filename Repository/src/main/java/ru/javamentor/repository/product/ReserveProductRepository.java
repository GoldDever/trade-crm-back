package ru.javamentor.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.javamentor.model.product.ReserveProduct;

public interface ReserveProductRepository extends JpaRepository<ReserveProduct, Long> {

    @Query("SELECT SUM (rp.productCount) FROM ReserveProduct rp WHERE rp.product.id = :productId")
    Integer countReserveProducts(@Param("productId") Long productId);
}
