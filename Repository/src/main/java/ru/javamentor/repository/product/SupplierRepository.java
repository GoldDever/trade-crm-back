package ru.javamentor.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.javamentor.dto.product.ManufacturerDto;
import ru.javamentor.dto.product.SupplierDto;
import ru.javamentor.model.product.Supplier;

import java.util.List;
import java.util.Set;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    @Query("SELECT new ru.javamentor.dto.product.SupplierDto(" +
            "s.id, " +
            "s.name, " +
            "s.idFromErp)" +
            "FROM Supplier s " +
            "WHERE s.id IN :supplierId")
    List<SupplierDto> findSupplierDtoBySupplierId(@Param("supplierId") List<Long> SupplierId);
}
