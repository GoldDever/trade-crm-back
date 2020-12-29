package ru.javamentor.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.javamentor.model.product.Product;

/**
 * Repository for Product
 */

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
