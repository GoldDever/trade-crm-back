package ru.javamentor.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.javamentor.model.product.ReserveProduct;

public interface ReserveProductRepository extends JpaRepository<ReserveProduct, Long> {

    @Query("SELECT (p.productCount - SUM(rp.productCount)) FROM ReserveProduct rp, Product p " +
            "WHERE rp.product.id = :productId and p.id = :productId GROUP BY p.productCount")
    Integer countReserveProducts(@Param("productId") Long productId);

    Boolean existsByProductId(Long productId);
}

