package ru.javamentor.controller.rest.manager.product;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javamentor.dto.product.ProductDto;
import ru.javamentor.service.product.ProductService;

@RestController
@RequestMapping("api/manager/product")
public class ManagerProductRestController {

    private final ProductService productService;

    public ManagerProductRestController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> removeProductReserve(@PathVariable Long productId) {
        return new ResponseEntity<>(productService.getProductDto(productId), HttpStatus.OK);
    }
}
