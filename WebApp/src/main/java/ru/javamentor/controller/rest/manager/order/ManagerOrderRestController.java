package ru.javamentor.controller.rest.manager.order;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.javamentor.dto.order.OrderApproveRequestDto;
import ru.javamentor.dto.order.OrderDto;
import ru.javamentor.model.user.Manager;
import ru.javamentor.model.user.User;
import ru.javamentor.service.client.ClientService;
import ru.javamentor.service.order.OrderApproveRequestService;
import ru.javamentor.service.order.OrderItemService;
import ru.javamentor.service.order.OrderService;
import ru.javamentor.service.product.ReserveProductService;


@RestController
@RequestMapping("api/manager/order")
public class ManagerOrderRestController {

    private final OrderService orderService;
    private final OrderItemService orderItemService;
    private final ClientService clientService;
    private final ReserveProductService reserveProductService;
    private final OrderApproveRequestService orderApproveRequestService;

    public ManagerOrderRestController(OrderService orderService,
                                      OrderItemService orderItemService,
                                      ClientService clientService, ReserveProductService reserveProductService, OrderApproveRequestService orderApproveRequestService) {
        this.orderService = orderService;
        this.orderItemService = orderItemService;
        this.clientService = clientService;
        this.reserveProductService = reserveProductService;
        this.orderApproveRequestService = orderApproveRequestService;
    }

    /**
     * Метод возвращает все заказы всех клиентов у менеджера
     *
     * @param manager - текущий пользователь
     * @return - результат выполнения
     */
    @GetMapping("/all")
    public ResponseEntity<?> getOrderDtoListByManager(@AuthenticationPrincipal Manager manager) {
        return new ResponseEntity<>(orderService.getAllOrderDtoListByManagerId(manager.getId()), HttpStatus.OK);
    }

    /**
     * Метод для получения списка заказов клиента на странице менеджера
     *
     * @param manager  - текущий пользователь
     * @param clientId - id клиента
     * @return - список заказов клиента
     */
    @GetMapping("/{clientId}/allOrders")
    public ResponseEntity<?> getAllClientOrders(@AuthenticationPrincipal Manager manager, @PathVariable Long clientId) {
        if (clientService.isExistsByClientId(clientId)) {
            if (clientService.relationClientWithManager(clientId, manager.getId())) {
                return new ResponseEntity<>(orderService.getOrderDtoListByClientId(clientId), HttpStatus.OK);
            } else {
                return ResponseEntity.badRequest().body("Клиент с Id - " + clientId + " не принадлежит текущему менеджеру");
            }
        }
        return ResponseEntity.badRequest().body("Нет клиента с Id - " + clientId);
    }


    /**
     * Метод для сохранения нового Order
     *
     * @param clientId - id клиента
     * @param user     - user из principal для получения manager
     * @return - статус http-запроса
     */
    @PostMapping(value = {"new/client/{clientId}", "new/client/"})
    public ResponseEntity<?> newOrder(@PathVariable(required = false) Long clientId,
                                      @AuthenticationPrincipal User user) {
        Long orderId = orderService.newOrder(clientId, user);
        return new ResponseEntity<>(orderId, HttpStatus.CREATED);
    }


    /**
     * GET метод для получения orderDTO на странице менеджера
     *
     * @param orderId - Принимает orderId как аргумент
     * @return - Возвращает orderDTO
     */
    @GetMapping(value = "/{orderId}")
    public ResponseEntity<?> getOrderDtoByOrderId(@PathVariable Long orderId) {
        if (orderService.isExistsByOrderId(orderId)) {
            OrderDto orderDto = orderService.getOrderDtoByOrderId(orderId);
            return ResponseEntity.status(HttpStatus.OK).body(orderDto);
        }
        return ResponseEntity.badRequest().body("Нет ордера с Id - " + orderId);
    }


    /**
     * Метод добавления нового OrderApproveRequest
     *
     * @param orderId             - Принимает orderId как аргумент
     * @param orderApproveRequest - объект запроса на согласование
     * @return - статус добавления запроса на утверждение заказа
     */
    @PostMapping(value = "/{orderId}/requestApprove")
    public ResponseEntity<String> addNewOrderApproveRequest(@PathVariable Long orderId, @RequestBody OrderApproveRequestDto orderApproveRequest) {
        if (orderService.isExistsByOrderId(orderId)) {
            try {
                orderApproveRequestService.saveOrderApproveRequest(orderApproveRequest);
                return ResponseEntity.status(HttpStatus.CREATED).body("Запрос на утверждение заказа успешно добавлен, id заказа=" + orderId);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Не удалось добавить запрос на утверждение заказа c id="
                        + orderId);
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Заказ с id = " + orderId + " не найден.");
    }

    @DeleteMapping(value = "/{orderId}")
    public ResponseEntity<String> deleteOrderByOrderId(@PathVariable Long orderId) {
        if (orderService.isExistsByOrderId(orderId)) {
            orderService.deleteOrderByOrderId(orderId);
            return ResponseEntity.status(HttpStatus.OK).body("Ордер успешно удален");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Во время удаления произошла ошибка");
    }

}
