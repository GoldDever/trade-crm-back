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
import ru.javamentor.service.product.ReserveProductService;


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
     * Метод добавления строки заказа
     *
     * @param orderItemDto - DTO строка заказа
     * @param orderId      - id заказа
     * @return - результат выполнения
     */
    @PostMapping("/{orderId}/addItem")
    public ResponseEntity<?> addItem(@RequestBody OrderItemDto orderItemDto,
                                     @PathVariable String orderId) {

        orderItemService.saveOrderItem(orderItemDto, orderId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Метод изменения количества товаров в строке заказа
     *
     * @param orderId      - id заказа
     * @param orderItemId  - id строки заказа
     * @param countProduct - количество на которое необходимо изменить
     * @return - результат выполнения
     */
    @PostMapping("/{orderId}/orderItem/{orderItemId}/count/{countProduct}")
    public ResponseEntity<?> changeProductCountInItem(@PathVariable Long orderId,
                                                      @PathVariable Long orderItemId,
                                                      @PathVariable Integer countProduct) {
        if (countProduct > 0) {
            orderItemService.editOrderItem(orderId, orderItemId, countProduct);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Введите корректное количество продукта", HttpStatus.BAD_REQUEST);
        }
    }
}
