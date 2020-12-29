package ru.javamentor.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javamentor.model.product.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
