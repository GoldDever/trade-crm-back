package ru.javamentor.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javamentor.model.product.ReserveProduct;

public interface ReserveProductRepository extends JpaRepository<ReserveProduct, Long> {
    ReserveProduct findByOrder_Id_AndProduct_Id(Long orderId, Long productId);
}
