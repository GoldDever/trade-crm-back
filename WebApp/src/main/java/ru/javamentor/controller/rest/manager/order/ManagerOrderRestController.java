package ru.javamentor.controller.rest.manager.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javamentor.dto.order.OrderItemDto;
import ru.javamentor.service.order.OrderItemService;


@RestController
@RequestMapping("api/manager/order")
public class ManagerOrderRestController {

    private final OrderItemService orderItemService;

    public ManagerOrderRestController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    /**
     * POST method add item to order
     *
     * @param orderItemDto DTO item and order
     * @param orderId      id of order
     * @return response http status entity
     */
    @PostMapping("/{orderId}/addItem")
    public ResponseEntity<?> addItem(@RequestBody OrderItemDto orderItemDto,
                                     @PathVariable String orderId) {

        orderItemService.saveOrderItem(orderItemDto, orderId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * POST method change the quantity of the product in Item
     *
     * @param orderItemDto DTO item and order
     * @param count of product
     * @param orderId id of order
     * @param orderItemId id of orderItem
     * @param productId id of product
     * @return response http status entity
     */
    @PostMapping("/{orderId}/orderItem/{orderItemId}/product/{productId}/count")
    public ResponseEntity<?> changeProductCountInItem(@RequestBody OrderItemDto orderItemDto,
                                                      Integer count,
                                                      @PathVariable Long orderId,
                                                      @PathVariable Long orderItemId,
                                                      @PathVariable Long productId) {
        orderItemService.editOrderItem(orderItemDto, count, orderId, orderItemId, productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
