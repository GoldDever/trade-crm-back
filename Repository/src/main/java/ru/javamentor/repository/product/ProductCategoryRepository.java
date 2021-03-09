package ru.javamentor.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.javamentor.dto.product.ProductCategoryDto;
import ru.javamentor.model.product.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
    @Query("SELECT new ru.javamentor.dto.product.ProductCategoryDto(" +
            "p.productCategory.id," +
            "p.productCategory.categoryName," +
            "p.productCategory.mainProductCategory.id)" +
            "FROM Product p " +
            "WHERE p.id = :productId")
    ProductCategoryDto findProductCategoryByProductId(@Param("productId") Long ProductId);
}
