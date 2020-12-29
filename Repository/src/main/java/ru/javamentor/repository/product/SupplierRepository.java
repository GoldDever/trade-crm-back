package ru.javamentor.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.javamentor.model.product.Supplier;

/**
 * Repository для Поставщика
 */
@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
