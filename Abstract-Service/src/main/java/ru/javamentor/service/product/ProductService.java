package ru.javamentor.service.product;

import ru.javamentor.dto.product.ProductDto;
import ru.javamentor.dto.product.ProductPostDto;
import ru.javamentor.model.product.Product;

import java.util.List;


/**
 * Abstract-Service для Продукта
 */
public interface ProductService {

    void saveProduct(ProductPostDto dto);
    void updateProduct(ProductPostDto dto);
    ProductDto getProductDtoByProductId(Long productId);
    boolean isProductIdExists(Long productId);
    Product getProductByIdFromErp(String idFromErp);
    void setProductImageUrl(Product product, String imageUrl);
    List<ProductDto> getProductListBySearch(String search);
    byte[] getProductImage(Long productId);
}
