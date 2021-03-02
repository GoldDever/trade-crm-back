package ru.javamentor.controller.rest.manager.product;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.javamentor.dto.product.ProductDto;
import ru.javamentor.service.product.ProductService;

import java.util.List;

@RestController
@RequestMapping("api/manager/product")
public class ManagerProductRestController {

    private final ProductService productService;

    public ManagerProductRestController(ProductService productService) {
        this.productService = productService;
    }

    /**
     *  Метод для получения продукта
     *
     * @param productId - id продукта
     * @return - продуктДТО
     */
    @GetMapping("/{productId}")
    public ResponseEntity<?> getProductDtoByProductId(@PathVariable Long productId) {
        if (productService.isProductIdExists(productId)){
            return ResponseEntity.ok().body(productService.getProductDtoByProductId(productId));
        }

        return ResponseEntity.badRequest().body("Продукт с id = " + productId + " не найден");
    }

    /**
     * Метод для поиска товаров по вхождению в наименование
     *
     * @param search - строка поиска
     * @return - Список товаров
     */
    @GetMapping()
    public ResponseEntity<?> getProductListBySearch(@RequestParam String search) {
        return ResponseEntity.ok(productService.getProductListBySearch(search));
    }
}
