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
import ru.javamentor.service.OrderItemService;
import ru.javamentor.service.ReserveProductService;


@RestController
@RequestMapping("api/manager/order")
public class ManagerOrderRestController {

    @Autowired
    OrderItemService orderItemService;

    @Autowired
    ReserveProductService removeProductReserveService;

    /**
     * POST method add item to order
     *
     * @param orderItemDto DTO item and order
     * @param orderId      id of order
     * @return response http status entity
     */
    @PostMapping(value = "/{orderId}/addItem")
    public ResponseEntity<?> addItem(@RequestBody OrderItemDto orderItemDto,
                                     @PathVariable String orderId) {

        orderItemService.saveOrderItem(orderItemDto, orderId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/{orderId}/product/{productId}/count/{productCount}/removeReserve")
    public ResponseEntity<String> removeProductReserve(@PathVariable String orderId,
                                                       @PathVariable String productId,
                                                       @PathVariable String productCount) {

        String responseBody = removeProductReserveService.removeProductReserve(orderId, productId, productCount);

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
}
