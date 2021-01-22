package ru.javamentor.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.javamentor.dto.product.ManufacturerDto;
import ru.javamentor.dto.product.ProductDto;
import ru.javamentor.model.product.Manufacturer;

/**
 * Repository для Производителя
 */

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
    @Query("SELECT new ru.javamentor.dto.product.ManufacturerDto(" +
            "m.id, " +
            "m.manufacturerName, " +
            "m.idFromErp) " +
            "FROM Manufacturer m " +
            "WHERE m.id = :productId")
    ManufacturerDto findManufacturerDtoByProductId(@Param("productId") Long ProductId);
}
