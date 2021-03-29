package ru.javamentor.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.javamentor.dto.product.ProductDto;
import ru.javamentor.model.product.Product;
import ru.javamentor.model.product.Supplier;

import java.util.List;
import java.util.Set;


public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findProductById(Long ProductId);

    Product findByIdFromErp(String idFromErp);

    @Query("SELECT new ru.javamentor.dto.product.ProductDto(" +
            "p.id, " +
            "p.productName, " +
            "p.madeCountry, " +
            "p.article, " +
            "p.price," +
            "p.productCount, " +
            "SUM (rp.productCount), " +
            "p.minMargin," +
            "p.standardMargin) " +
            "FROM Product p " +
            "LEFT OUTER JOIN ReserveProduct rp ON p.id = rp.product " +
            "WHERE p.id = :productId " +
            "GROUP BY p.id")
    ProductDto findProductDtoByProductId(@Param("productId") Long ProductId);

    @Query("SELECT p.manufacturer.id " +
            "FROM Product p " +
            "WHERE p.id = :productId")
    Long findManufacturerIdByProductId(@Param("productId") Long ProductId);

    @Query("SELECT p.unit.id " +
            "FROM Product p " +
            "WHERE p.id = :productId")
    Long findUnitIdByProductId(@Param("productId") Long ProductId);

    @Query("SELECT s.id " +
            "FROM Product p " +
            "JOIN p.suppliers s " +
            "WHERE p.id = :productId")
    List<Long> findListSupplierIdByProductId(@Param("productId") Long ProductId);



    @Query("SELECT new ru.javamentor.dto.product.ProductDto(" +
            "p.id, " +
            "p.productName, " +
            "p.madeCountry, " +
            "p.article, " +
            "p.price," +
            "p.productCount, " +
            "SUM (rp.productCount), " +
            "p.minMargin," +
            "p.standardMargin) " +
            "FROM Product p " +
            "LEFT OUTER JOIN ReserveProduct rp ON p.id = rp.product " +
            "WHERE lower(p.productName) like concat('%', lower( :search), '%') " +
            "GROUP BY p.id")
    List<ProductDto> findByProductNameIgnoreCaseContaining(String search);

    @Query("SELECT new ru.javamentor.dto.product.ProductDto(" +
            "p.id, " +
            "p.productName, " +
            "p.madeCountry, " +
            "p.article, " +
            "p.price," +
            "p.productCount, " +
            "SUM (rp.productCount), " +
            "p.minMargin," +
            "p.standardMargin) " +
            "FROM Product p " +
            "LEFT OUTER JOIN ReserveProduct rp ON p.id = rp.product " +
            "GROUP BY p.id")
    List<ProductDto> findAllProductDto();

    @Query("SELECT p.ImageUrl " +
            "FROM Product p " +
            "WHERE p.id = :productId")
    String findImageUrlByProductId(@Param("productId") Long ProductId);

}
