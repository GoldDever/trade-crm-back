package ru.javamentor.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javamentor.model.product.ReserveProduct;

public interface ReserveProductRepository extends JpaRepository<ReserveProduct, Long> {

}
