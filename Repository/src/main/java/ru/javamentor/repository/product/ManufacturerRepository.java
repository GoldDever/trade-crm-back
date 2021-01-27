package ru.javamentor.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.javamentor.dto.product.ManufacturerDto;
import ru.javamentor.dto.product.ProductDto;
import ru.javamentor.model.product.Manufacturer;
import ru.javamentor.model.product.Product;

/**
 * Repository для Производителя
 */

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
    Manufacturer findManufacturerById(Long ManufacturerId);
    @Query("SELECT new ru.javamentor.dto.product.ManufacturerDto(" +
            "m.id, " +
            "m.manufacturerName, " +
            "m.idFromErp) " +
            "FROM Manufacturer m " +
            "WHERE m.id = :manufacturerId")
    ManufacturerDto findManufacturerDtoByManufacturerId(@Param("manufacturerId") Long ManufacturerId);
}
