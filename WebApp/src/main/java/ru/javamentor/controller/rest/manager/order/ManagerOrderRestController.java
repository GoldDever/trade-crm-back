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
     * Controller to remove reserve
     * or product from reserve
     *
     * @param orderId      id of order
     * @param productId    id of product
     * @param productCount count of products
     * @return response http status entity with body
     */
    @PostMapping("/{orderId}/product/{productId}/count/{productCount}/removeReserve")
    public ResponseEntity<String> removeProductReserve(@PathVariable String orderId,
                                                       @PathVariable String productId,
                                                       @PathVariable String productCount) {

        String responseBody = removeProductReserveService.removeProductReserve(orderId, productId, productCount);

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
}
