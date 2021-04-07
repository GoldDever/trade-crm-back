package ru.javamentor.controller.rest.admin.order;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javamentor.dto.order.OrderApproveAnswerDto;
import ru.javamentor.service.order.OrderApproveAnswerService;
import ru.javamentor.service.order.OrderService;

@RestController
@RequestMapping("api/admin/order")
public class AdminOrderRestController {

    private final OrderService orderService;
    private final OrderApproveAnswerService orderApproveAnswerService;

    public AdminOrderRestController(OrderService orderService, OrderApproveAnswerService orderApproveAnswerService) {
        this.orderService = orderService;
        this.orderApproveAnswerService = orderApproveAnswerService;
    }


    /**
     * Метод получает новый флаг approve и устанавливает его в Order
     */
    @PostMapping(value = "/{orderId}/approve/")
    public ResponseEntity<String> changeApproveStatus(@RequestBody OrderApproveAnswerDto orderApproveAnswer, @PathVariable Long orderId) {
        orderService.updateApproveStatus(orderApproveAnswer, orderId);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    /**
     * Метод изменяет статус заказа на Отгружено.
     * Если не находит нужный заказ возвращает HTTP ошибку 400
     *
     * @param orderIdFromErp - идентификатор заказа из ERP системы
     * @return - результат выполнения
     */
    @PostMapping("/{orderIdFromErp}/shipped")
    public ResponseEntity<String> shippedOrder(@PathVariable String orderIdFromErp) {
        Long orderId = orderService.updateShippedStatus(orderIdFromErp);
        if (orderId == null) {
            return new ResponseEntity<>("Заказ не найден.", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }


    /**
     * Метод добавления нового OrderApproveAnswer
     *
     * @param orderId
     * @param orderApproveAnswer
     * @return - статус добавления ответа на утверждение заказа
     */
    @PostMapping(value = "/{orderId}/answerApprove")
    public ResponseEntity<String> addNewOrderApproveAnswer(@RequestBody OrderApproveAnswerDto orderApproveAnswer, @PathVariable Long orderId) {
        try {
            orderApproveAnswerService.saveOrderApproveAnswer(orderApproveAnswer);
            orderService.updateApproveStatus(orderApproveAnswer, orderId);
            return ResponseEntity.status(HttpStatus.CREATED).body("Ответ на утверждение заказа успешно добавлен, id заказа=" + orderId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Не удалось добавить ответ на утверждение заказа c id="
                    + orderId);
        }
    }

}
