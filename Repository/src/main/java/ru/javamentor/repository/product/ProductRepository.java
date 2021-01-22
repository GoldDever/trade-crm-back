package ru.javamentor.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.javamentor.dto.product.ProductDto;
import ru.javamentor.model.product.Product;
import ru.javamentor.model.product.ProductCategory;


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
}
