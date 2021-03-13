package ru.javamentor.controller.rest.manager.reserve;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javamentor.dto.product.ReserveProductDto;
import ru.javamentor.service.order.OrderService;
import ru.javamentor.service.product.ProductService;
import ru.javamentor.service.product.ReserveProductService;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * Метод возвращает список резервов товара по переданному productId
     *
     * @param productId
     * @return
     */
    @GetMapping("/all/{productId}")
    public ResponseEntity<?> getAllReserveProductByProductId(@PathVariable String productId) {
        try {
            List<ReserveProductDto> reserveProductDtoList = reserveProductService
                    .getListReserveProductDtoByProductId(Long.valueOf(productId));

            if (reserveProductDtoList.size() > 0) {
                return ResponseEntity.ok().body(reserveProductDtoList);
            } else {
                return ResponseEntity.badRequest().body("Отсутствуют резервы по данному товару.");
            }

        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body("Произошла ошибка при попытке получить информацию по резервам на Товар с id = " + productId);
        }
    }

    /**
     * @param orderId   - id заказа
     * @param productId - id продукта
     * @return - количество зарезервированных продуктов в заказе
     */
    @GetMapping("/count/order/{orderId}/product/{productId}")
    public ResponseEntity<?> getCountReservedProductByOrderIdAndProductId(@PathVariable Long orderId,
                                                                          @PathVariable Long productId) {
        if (orderService.isExistsByOrderId(orderId) && productService.isProductIdExists(productId)) {
            Integer reserveProductCount = reserveProductService.getCountReservedProductByOrderIdAndProductId(orderId, productId);
            if (reserveProductCount == null) {
                return ResponseEntity.badRequest().body("Отсутствует резерв продукта с id = " + productId
                        + " в заказе с id = " + orderId);
            }
            return ResponseEntity.ok().body(reserveProductCount);
        }
        return ResponseEntity.badRequest().body("Отсутствует продукт с id = " + productId
                + " или заказ с id = " + orderId);
    }

    /**
     * Метод для добавления резерва по orderId
     *
     * @param orderId - id заказа
     * @return - HTTP ответ с BODY
     */
    @GetMapping("/{orderId}/all/addReserve")
    public ResponseEntity<String> addProductReserveForAllProductInOrder(@PathVariable Long orderId) {
        String result = reserveProductService.addReserveByOrder(orderId);
        if (result.isEmpty()) {
            return new ResponseEntity<>("Товар зарезирвирован", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Часть товаров не может быть зарезирвированна: \n" + result, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * POST метод для резервирования продукта
     *
     * @param orderId      - id Order
     * @param productId    - id продукта по которому сохраняется резерв
     * @param productCount - количество продукта, которое необходимо зарезервировать
     * @return - сообщение о состоянии HTTP-ответа
     */
    @PostMapping("/count/order/{orderId}/product/{productId}/count/{productCount}/addReserve")
    public ResponseEntity<String> addProductReserve(@PathVariable Long orderId,
                                                    @PathVariable Long productId,
                                                    @PathVariable Integer productCount) {
        String response = reserveProductService.saveProductReserve(orderId, productId, productCount);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    /**
     * Метод для удаления
     * зарезирвированного продукта
     *
     * @param orderId      - id заказа
     * @param productId    - id продукта
     * @param productCount - количество удалеямого продукта из резерва
     * @return - HTTP ответ с BODY
     */
    @GetMapping("/count/order/{orderId}/product/{productId}/count/{productCount}/removeReserve")
    public ResponseEntity<String> removeProductReserve(@PathVariable Long orderId,
                                                       @PathVariable Long productId,
                                                       @PathVariable Integer productCount) {
        return new ResponseEntity<>(
                reserveProductService.removeProductReserve(orderId, productId, productCount), HttpStatus.OK);
    }
}
