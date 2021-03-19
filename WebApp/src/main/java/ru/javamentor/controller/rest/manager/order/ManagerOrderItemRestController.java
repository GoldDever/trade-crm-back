package ru.javamentor.controller.rest.manager.order;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javamentor.dto.order.OrderItemDto;
import ru.javamentor.model.order.OrderItem;
import ru.javamentor.service.order.OrderItemService;
import ru.javamentor.service.order.OrderService;

import java.math.BigDecimal;


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
     *
     * @param orderItemDto
     */
    @PostMapping
    public ResponseEntity<String> editCountInOrderItem(@RequestBody OrderItemDto orderItemDto) {
        try {
            orderItemService.editOrderItem(orderItemDto.getId(), orderItemDto.getProductCount());
            return ResponseEntity.status(HttpStatus.OK).body("Строка id=" + orderItemDto.getId()
                    + " успешно изменена.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Не удалось обновить строку id="
                    + orderItemDto.getId());
        }
    }

    /**
     * Метод удаления orderItem
     *
     * @param orderItemId
     */
    @DeleteMapping("/delete/{orderItemId}")
    public ResponseEntity<String> deleteOrderItem(@PathVariable Long orderItemId) {
        try {
            orderItemService.deleteOrderItem(orderItemId);
            return ResponseEntity.status(HttpStatus.OK).body("Строка id=" + orderItemId
                    + " успешно удалена.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Не удалось удалить строку id="
                    + orderItemId);
        }
    }

    /**
     * Метод добавления нового orderItem в order
     *
     * @param orderId
     * @param orderItemDto
     */
    @PostMapping(value = "/addOrderItem/{orderId}")
    public ResponseEntity<String> newOrderItem(@PathVariable Long orderId, @RequestBody OrderItemDto orderItemDto) {
        try {
            orderItemService.saveOrderItem(orderId, orderItemDto);
            return ResponseEntity.status(HttpStatus.OK).body("Новая строка успешно добавлена");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Не удалось добавить строку в заказ id="
                    + orderId);
        }
    }

    /**
     * Метод изменяет currentMargePercent в соответствии с входящей ценой
     *
     * @param newPrice
     * @param orderItemId
     * @param orderItemDto
     */
    @PutMapping(value = "/{orderItemId}/{newPrice}")
    public ResponseEntity<String> editProductPrice(@PathVariable Long orderItemId, @PathVariable Double newPrice,
                                                   @RequestBody OrderItemDto orderItemDto) {

        OrderItem orderItem = orderItemService.getOrderItemByDTO(orderItemDto);
        BigDecimal price = orderItem.getProduct().getPrice();
        BigDecimal currentMargeRub = BigDecimal.valueOf(newPrice).subtract(price);
        BigDecimal currentMargePercent = currentMargeRub.multiply(BigDecimal.valueOf(100)).divide(price);

        if (currentMargePercent.compareTo(orderItem.getProduct().getMinMargin()) > 0) {
            orderItem.setCurrentMargePercent(currentMargePercent);
        } else {
            orderItem.setCurrentMargePercent(orderItem.getProduct().getMinMargin());
        }

        try {
            orderItemService.saveOrderItem(orderItemId, orderItemDto);
            return ResponseEntity.status(HttpStatus.OK).body("Значение маржи изменено");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Не удалось изменить маржу в строке заказа id="
                + orderItemId);
        }
    }

}
