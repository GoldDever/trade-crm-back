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
import ru.javamentor.dto.order.OrderDto;
import ru.javamentor.dto.order.OrderItemDto;
import ru.javamentor.service.order.OrderItemService;
import ru.javamentor.service.order.OrderService;


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
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Не удалось обновить строку id="
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
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Не удалось удалить строку id="
                    + orderItemId);
        }
    }

    /**
     * Метод добавления новой строки в заказ
     *
     * @param orderId      - id заказа
     * @param orderItemDto - объект новой строки заказа
     */
    @PostMapping(value = "/addOrderItem/{orderId}")
    public ResponseEntity<String> newOrderItem(@PathVariable Long orderId, @RequestBody OrderItemDto orderItemDto) {
        try {
            orderItemService.saveOrderItem(orderId, orderItemDto);
            return ResponseEntity.status(HttpStatus.OK).body("Новая строка успешно добавлена");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Не удалось добавить строку в заказ id="
                    + orderId);
        }
    }
    /**
     * Метод сохраняет измененный order
     *
     * @param orderDto - DTO из которого получаем order
     */
    @PutMapping(value = "/update")
    public ResponseEntity<?> updateOrder(@RequestBody OrderDto orderDto) {
        orderService.updateOrderDto(orderDto);
        return ResponseEntity.ok().build();
    }

    /**
     * Метод изменяет currentMargePercent в соответствии с входящей ценой
     *
     * @param newPrice    - новая цена
     * @param orderItemId - id строки в заказе
     */
    @PutMapping(value = "/{orderItemId}/{newPrice}")
    public ResponseEntity<String> editProductPrice(@PathVariable Long orderItemId, @PathVariable Double newPrice) {

        if (orderItemService.isExistsByOrderItemId(orderItemId)) {
            orderItemService.editProductPrice(orderItemId, newPrice);
            return ResponseEntity.status(HttpStatus.OK).body("Значение наценки изменено");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Нет orderItem с id - " + orderItemId);
    }

}
