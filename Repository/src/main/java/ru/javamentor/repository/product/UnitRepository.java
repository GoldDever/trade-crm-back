package ru.javamentor.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.javamentor.model.product.Unit;

/**
 * Repository для единицы измерения
 */

@Repository
public interface UnitRepository extends JpaRepository<Unit, Long> {
}
