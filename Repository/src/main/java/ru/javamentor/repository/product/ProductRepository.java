package ru.javamentor.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javamentor.model.product.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findProductById(Long ProductId);
    Product findByIdFromErp(String idFromErp);
}
