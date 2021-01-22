package ru.javamentor.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.javamentor.dto.product.ManufacturerDto;
import ru.javamentor.dto.product.UnitDto;
import ru.javamentor.model.product.Unit;

public interface UnitRepository extends JpaRepository<Unit, Long> {
    @Query("SELECT new ru.javamentor.dto.product.UnitDto(" +
            "u.id, " +
            "u.unitName) " +
            "FROM Unit u " +
            "WHERE u.id = :productId")
    UnitDto findUnitDtoByProductId(@Param("productId") Long ProductId);
}

