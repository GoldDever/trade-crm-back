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
     * Метод возвращает список резервов товара по переданному productId (среди всех заказов).
     * Включая Имя и Фамилию Менеджера оформившего заказ.
     *
     * @param productId - id продукта
     * @return List<ReserveProductDto> - список резервов да данный товар
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
     * Метод возвращает резервы товара с productId в конкретном заказе
     *
     * @param orderId   - id заказа
     * @param productId - id продукта
     * @return List<ReserveProductDto> - список резервов на товар в текущем заказе
     */
    @GetMapping("/all/order/{orderId}/product/{productId}")
    public ResponseEntity<?> getAllReserveProductByOrderIdAndProductId(@PathVariable Long orderId,
                                                                       @PathVariable Long productId) {
        try {
            List<ReserveProductDto> reserveProductDtoList = reserveProductService
                    .getListReserveProductDtoByOrderIdAndProductId(orderId, productId);

            if (reserveProductDtoList.size() > 0) {
                return ResponseEntity.ok().body(reserveProductDtoList);
            } else {
                return ResponseEntity.badRequest().body("В заказе №" + orderId + "отсутствуют резервы по данному товару.");
            }

        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body("Произошла ошибка при попытке получить информацию по резервам в заказе с id = " + orderId +
                            " на Товар с id = " + productId);
        }

    }

    /**
     * Метод возвращает Количество зарезервированных товаров в ордере по orderId и productId
     *
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
     * Метод для добавления резерва полностью на заказ
     *
     * @param orderId - id заказа
     * @return - Результат выполнения запроса
     */
    @GetMapping("/{orderId}/all/addReserve")
    public ResponseEntity<String> addProductReserveForAllProductInOrder(@PathVariable Long orderId) {
        String result = reserveProductService.addReserveByOrder(orderId);
        if (result.isEmpty()) {
            return new ResponseEntity<>("Товар зарезервирован", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Часть товаров не может быть зарезервирована: " + " \n" + result, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Метод для проверки есть ли в заказе незарезервированные продукты
     *
     * @param orderId - id заказа
     * @return - Результат выполнения запроса
     */
    @GetMapping("/{orderId}/all/isReserved")
    public ResponseEntity<String> isAllProductsReservedInOrder(@PathVariable Long orderId) {
        if (reserveProductService.isAllProductReservedByOrder(orderId)) {
            return ResponseEntity.ok("Все товары в заказе зарезервированы");
        } else {
            return ResponseEntity.badRequest().body("В заказе есть незарезервированные товары");
        }
    }

    /**
     * Метод для резервирования продукта
     *
     * @param orderId      - id Order
     * @param productId    - id продукта по которому сохраняется резерв
     * @param productCount - количество продукта, которое необходимо зарезервировать
     * @return - Результат выполнения запроса
     */
    @PostMapping("/count/order/{orderId}/product/{productId}/count/{productCount}/addReserve")
    public ResponseEntity<String> addProductReserve(@PathVariable Long orderId,
                                                    @PathVariable Long productId,
                                                    @PathVariable Integer productCount) {
        return ResponseEntity.ok(reserveProductService.saveProductReserve(orderId, productId, productCount));
    }


    /**
     * Метод для удаления снятия с резерва определенного количества товара
     *
     * @param orderId      - id заказа
     * @param productId    - id продукта
     * @param productCount - количество продуктов для снятия резерва
     * @return - Результат выполнения запроса
     */
    @GetMapping("/count/order/{orderId}/product/{productId}/count/{productCount}/removeReserve")
    public ResponseEntity<String> removeProductReserve(@PathVariable Long orderId,
                                                       @PathVariable Long productId,
                                                       @PathVariable Integer productCount) {
        return ResponseEntity.ok(reserveProductService.removeProductReserve(orderId, productId, productCount));
    }
}
