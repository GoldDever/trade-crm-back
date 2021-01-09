package ru.javamentor.controller.rest.admin.order;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javamentor.dto.order.OrderApproveDto;
import ru.javamentor.service.order.OrderService;

@RestController
@RequestMapping("api/admin/order")
public class AdminOrderRestController {

    private final OrderService orderService;

    public AdminOrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }


    /**
     * Метод получает новый флаг approve и устанавливает его в Order
     */
    @PostMapping(value = "/{orderId}/approve/")
    public ResponseEntity<String> changeApproveStatus(@RequestBody OrderApproveDto orderApproveDto, @PathVariable Long orderId) {
        orderService.updateApproveStatus(orderApproveDto, orderId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Метод изменяет статус заказа
     * на Отгружено
     *
     * @param orderIdFromErp - идентификатор заказа из ERP системы
     * @return - результат выполнения
     */
    @PostMapping("/{orderIdFromErp}/shipped")
    public ResponseEntity<String> shippedOrder(@PathVariable String orderIdFromErp) {
        orderService.updateShippedStatus(orderIdFromErp);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
