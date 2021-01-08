package ru.javamentor.controller.rest.manager.order;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javamentor.dto.order.OrderItemDto;
import ru.javamentor.service.product.ReserveProductService;
import ru.javamentor.service.order.OrderItemService;


@RestController
@RequestMapping("api/manager/order")
public class ManagerOrderRestController {

    private final OrderItemService orderItemService;
    private final ReserveProductService removeProductReserveService;

    public ManagerOrderRestController(OrderItemService orderItemService,
                                      ReserveProductService removeProductReserveService) {
        this.orderItemService = orderItemService;
        this.removeProductReserveService = removeProductReserveService;
    }

    /**
     * POST метод для добавления товара в заказ
     *
     * @param orderItemDto - DTO для OrderItem
     * @param orderId      - id заказа
     * @return - HTTP ответ
     */
    @PostMapping("/{orderId}/addItem")
    public ResponseEntity<?> addItem(@RequestBody OrderItemDto orderItemDto,
                                     @PathVariable String orderId) {

        orderItemService.saveOrderItem(orderItemDto, orderId);

        return new ResponseEntity<>(HttpStatus.OK);
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
    @PostMapping("/{orderId}/product/{productId}/count/{productCount}/removeReserve")
    public ResponseEntity<String> removeProductReserve(@PathVariable Long orderId,
                                                       @PathVariable Long productId,
                                                       @PathVariable Integer productCount) {

        String responseBody = removeProductReserveService.removeProductReserve(orderId, productId, productCount);

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
}
