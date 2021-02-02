package ru.javamentor.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javamentor.model.product.*;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findProductById(Long ProductId);
    Product findProductByIdFromErp(String IdFromErp);
}



