package ru.javamentor.controller.rest.manager.reserve;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javamentor.service.order.OrderService;
import ru.javamentor.service.product.ProductService;
import ru.javamentor.service.product.ReserveProductService;

@RestController
@RequestMapping("api/manager/productReserve")
public class ManagerProductReserveRestController {

    private final ReserveProductService reserveProductService;
    private final ProductService productService;
    private final OrderService orderService;

    public ManagerProductReserveRestController(ReserveProductService reserveProductService, ProductService productService, OrderService orderService) {
        this.reserveProductService = reserveProductService;
        this.productService = productService;
        this.orderService = orderService;
    }

    @GetMapping("/all/{productId}")
    public ResponseEntity<?> getAllReserveProductByProductId(@PathVariable String productId) {
        //TODO Метод принимает productId и возвращает List<ReserveProductDto> которые относятся к данному продукту
        return null;
    }

    @GetMapping("/count/order/{orderId}/product/{productId}")
    public ResponseEntity<?> getCountReservedProductByOrderIdAndProductId(@PathVariable Long orderId, @PathVariable Long productId) {
        {
            if (orderService.isExistsByOrderId(orderId)) {
                if (productService.isProductIdExists(productId)) {
                    Integer reserveProductCount = reserveProductService.getCountReservedProductByOrderIdAndProductId(orderId, productId);
                    if (reserveProductCount == null) {
                        return ResponseEntity.badRequest().body("Отсутствует резерв продукта с id = " + productId + " в заказе с id = " + orderId);
                    } else {
                        return ResponseEntity.ok().body(reserveProductCount);
                    }
                } else {
                    return ResponseEntity.badRequest().body("Отсутствует продукт с id = " + productId);
                }
            } else {
                return ResponseEntity.badRequest().body("Отсутствует заказ с id = " + orderId);
            }
        }
    }
}



