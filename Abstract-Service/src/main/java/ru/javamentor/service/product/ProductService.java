package ru.javamentor.service.product;

import org.springframework.web.multipart.MultipartFile;
import ru.javamentor.dto.product.ProductDto;
import ru.javamentor.dto.product.ProductPostDto;
import ru.javamentor.model.product.Product;

import java.io.IOException;


/**
 * Abstract-Service для Продукта
 */
public interface ProductService {

    void saveProduct(ProductPostDto dto);
    void updateProduct(ProductPostDto dto);
    ProductDto getProductDtoByProductId(Long productId);
    boolean isProductIdExists(Long productId);
    Product getProductByIdFromErp(String idFromErp);
    void imageUpdateProduct(Product product, MultipartFile image);
}
