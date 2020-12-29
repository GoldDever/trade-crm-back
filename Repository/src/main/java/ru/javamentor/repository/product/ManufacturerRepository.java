package ru.javamentor.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.javamentor.model.product.Manufacturer;

/**
 * Repository для Производителя
 */

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
}
