package ru.javamentor.controller.rest.manager.order;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javamentor.dto.order.OrderItemDto;
import ru.javamentor.service.order.OrderItemService;
import ru.javamentor.service.product.ReserveProductService;


@RestController
@RequestMapping("api/manager/order")
public class ManagerOrderRestController {

    private final OrderItemService orderItemService;
    private final ReserveProductService reserveProductService;

    public ManagerOrderRestController(OrderItemService orderItemService, ReserveProductService reserveProductService) {

        this.orderItemService = orderItemService;
        this.reserveProductService = reserveProductService;
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
     *
     * @param orderId
     * @param productId
     * @param productCount
     * @return
     */
    @PostMapping("/{orderId}/product/{productId}/count/{productCount}/addReserve")
    public ResponseEntity<?> addProductReserve(@PathVariable Long orderId,
                                               @PathVariable Long productId,
                                               @PathVariable Integer productCount) {

        reserveProductService.saveProductReserve(orderId, productId, productCount);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
