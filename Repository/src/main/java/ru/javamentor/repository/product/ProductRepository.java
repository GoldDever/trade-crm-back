package ru.javamentor.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.javamentor.dto.product.ProductDto;
import ru.javamentor.model.product.Product;
import ru.javamentor.model.product.Supplier;

import java.util.Set;


public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findProductById(Long ProductId);

    Product findByIdFromErp(String idFromErp);

    @Query("SELECT new ru.javamentor.dto.product.ProductDto(" +
            "p.id, " +
            "p.productName, " +
            "p.madeCountry, " +
            "p.article) " +
            "FROM Product p " +
            "WHERE p.id = :productId")
    ProductDto findProductDtoByProductId(@Param("productId") Long ProductId);

    @Query("SELECT p.manufacturer.id " +
            "FROM Product p " +
            "WHERE p.id = :productId")
    Long findManufacturerIdByProductId(@Param("productId") Long ProductId);

    @Query("SELECT p.unit.id " +
            "FROM Product p " +
            "WHERE p.id = :productId")
    Long findUnitIdByProductId(@Param("productId") Long ProductId);

    @Query("SELECT p.suppliers " +
            "FROM Product p " +
            "WHERE p.id = :productId")
    Set<Supplier> findSupplierByProductId(@Param("productId") Long ProductId);


}
