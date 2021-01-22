package ru.javamentor.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.javamentor.dto.product.ManufacturerDto;
import ru.javamentor.dto.product.SupplierDto;
import ru.javamentor.model.product.Supplier;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
