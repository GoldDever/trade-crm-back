package ru.javamentor.controller.rest.manager.order;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javamentor.dto.order.OrderDto;
import ru.javamentor.dto.order.OrderItemDto;
import ru.javamentor.model.order.OrderItem;
import ru.javamentor.service.order.OrderItemService;
import ru.javamentor.service.order.OrderService;

import java.util.List;

@RestController
@RequestMapping("api/manager/orderitem")
public class ManagerOrderItemRestController {

    private final OrderService orderService;
    private final OrderItemService orderItemService;

    public ManagerOrderItemRestController(OrderService orderService, OrderItemService orderItemService) {
        this.orderService = orderService;
        this.orderItemService = orderItemService;
    }


    /**
     * Метод изменения количества товара в orderItem
     * @param orderItemDto
     */
    @PostMapping
    public ResponseEntity<OrderDto> editCountInOrderItem(@RequestBody OrderItemDto orderItemDto){

        orderItemService.editOrderItemCount( orderItemDto.getId(), orderItemDto.getProductCount());

        OrderDto orderDto = orderService.getOrderDtoByOrderId(orderItemDto.getOrderId());
        return ResponseEntity.status(HttpStatus.OK).body(orderDto);
    }

    /**
     *  Метод удаления orderItem
     * @param orderItemDto
     */
    @DeleteMapping
    public ResponseEntity<OrderDto> deleteOrderItem( @RequestBody OrderItemDto orderItemDto){
        orderItemService.deleteOrderItem(orderItemDto);
        OrderDto orderDto = orderService.getOrderDtoByOrderId(orderItemDto.getOrderId());
        return ResponseEntity.status(HttpStatus.OK).body(orderDto);
    }

    /**
     * Метод добавления нового orderItem в order
     * @param orderItemDto
     */
    @PostMapping(value = "/addOrderItem")
    public ResponseEntity<OrderDto> newOrderItem(@RequestBody OrderItemDto orderItemDto){
        orderItemService.saveOrderItem(orderItemDto);
        OrderDto orderDto = orderService.getOrderDtoByOrderId(orderItemDto.getOrderId());
        return ResponseEntity.status(HttpStatus.OK).body(orderDto);
    }
}
