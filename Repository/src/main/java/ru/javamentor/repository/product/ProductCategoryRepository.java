package ru.javamentor.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.javamentor.model.product.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
    @Query("SELECT p.productCategory " +
            "FROM Product p " +
            "WHERE p.id = :productId")
    ProductCategory findProductCategoryByProductId(@Param("productId") Long ProductId);
}
