package ru.javamentor.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javamentor.model.product.Unit;

public interface UnitRepository extends JpaRepository<Unit, Long> {
}
