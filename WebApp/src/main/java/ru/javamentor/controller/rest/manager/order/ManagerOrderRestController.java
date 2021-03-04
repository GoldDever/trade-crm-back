package ru.javamentor.controller.rest.manager.order;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javamentor.dto.order.OrderDto;
import ru.javamentor.dto.order.OrderItemDto;
import ru.javamentor.model.user.Manager;
import ru.javamentor.model.user.User;
import ru.javamentor.service.client.ClientService;
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

    public ManagerOrderRestController(OrderService orderService,
                                      OrderItemService orderItemService,
                                      ClientService clientService, ReserveProductService reserveProductService) {
        this.orderService = orderService;
        this.orderItemService = orderItemService;
        this.clientService = clientService;
        this.reserveProductService = reserveProductService;
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
     * @param manager - текущий пользователь
     * @param clientId - id клиента
     * @return - список заказов клиента
     */
    @GetMapping("/{clientId}/allOrders")
    public ResponseEntity<?> getAllClientOrders(@AuthenticationPrincipal Manager manager, @PathVariable Long clientId) {
        if (clientService.isExistsByClientId(clientId)) {
            if(clientService.relationClientWithManager(clientId, manager.getId())) {
                return new ResponseEntity<>(orderService.getOrderDtoListByClientId(clientId), HttpStatus.OK);
            } else {
                return ResponseEntity.badRequest().body("Клиент с Id - " + clientId +" не принадлежит текущему менеджеру");
            }
        }
        return ResponseEntity.badRequest().body("Нет клиента с Id - " + clientId);
    }

    /**
     * Метод добавления строки заказа
     *
     * @param orderItemDto - DTO строка заказа
     * @param orderId - id заказа
     * @return - результат выполнения
     */
    @PostMapping("/{orderId}/addItem")
    public ResponseEntity<?> addItem(@RequestBody OrderItemDto orderItemDto,
                                     @PathVariable String orderId) {

        orderItemService.saveOrderItem(orderItemDto, orderId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Метод изменения количества товаров в строке заказа
     *
     * @param orderId - id заказа
     * @param orderItemId - id строки заказа
     * @param countProduct - количество на которое необходимо изменить
     * @return - результат выполнения
     */
    @PostMapping("/{orderId}/orderItem/{orderItemId}/count/{countProduct}")
    public ResponseEntity<?> changeProductCountInItem(@PathVariable Long orderId,
                                                      @PathVariable Long orderItemId,
                                                      @PathVariable Integer countProduct) {
        if (countProduct > 0) {
            orderItemService.editOrderItem(orderId, orderItemId, countProduct);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Введите корректное количество продукта", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Метод для удаления
     * зарезирвированного продукта
     *
     * @param orderId - id заказа
     * @param productId - id продукта
     * @param productCount - количество удалеямого продукта из резерва
     * @return - HTTP ответ с BODY
     */
    @GetMapping("/{orderId}/product/{productId}/count/{productCount}/removeReserve")
    public ResponseEntity<String> removeProductReserve(@PathVariable Long orderId,
                                                       @PathVariable Long productId,
                                                       @PathVariable Integer productCount) {
        return new ResponseEntity<>(
                reserveProductService.removeProductReserve(orderId, productId, productCount), HttpStatus.OK);
    }

    /**
     * Метод для сохранения нового Order
     *
     * @param clientId - id клиента
     * @param user     - user из principal для получения manager
     * @return - статус http-запроса
     */
    @PostMapping("new/client/{clientId}")
    public ResponseEntity<?> newOrder(@PathVariable Long clientId,
                                      @AuthenticationPrincipal User user) {
        orderService.newOrder(clientId, user);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * POST метод для резервирования продукта
     *
     * @param orderId - id Order
     * @param productId - id продукта по которому сохраняется резерв
     * @param productCount - количество продукта, которое необходимо зарезервировать
     * @return - сообщение о состоянии HTTP-ответа
     */
    @PostMapping("/{orderId}/product/{productId}/count/{productCount}/addReserve")
    public ResponseEntity<String> addProductReserve(@PathVariable Long orderId,
                                                    @PathVariable Long productId,
                                                    @PathVariable Integer productCount) {
        String response = reserveProductService.saveProductReserve(orderId, productId, productCount);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Метод для добавления резерва по orderId
     *
     * @param orderId - id заказа
     * @return - HTTP ответ с BODY
     */
    @GetMapping("/{orderId}/all/addReserve")
    public ResponseEntity<String> addOrderReserve(@PathVariable Long orderId){
        String result = reserveProductService.addReserveByOrder(orderId);
        if(result.isEmpty()){
            return new ResponseEntity<>("Товар зарезирвирован", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Часть товаров не может быть зарезирвированна: \n"+result, HttpStatus.BAD_REQUEST);
        }
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

    /** Метод изменения количества товара в orderItem
     */
    @PostMapping(value = "/{orderId}")
    public ResponseEntity<?> editCountInOrderItem(@PathVariable Long orderId, @RequestBody OrderItemDto orderItemDto){
        if (orderService.isExistsByOrderId(orderId)) {
            orderItemService.editOrderItem(orderId, orderItemDto.getId(), orderItemDto.getProductCount());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**Метод удаления orderItem
     *
     */
    @DeleteMapping(value = "/{orderId}")
    public ResponseEntity<?> deleteOrderItem(@PathVariable Long orderId, @RequestBody OrderItemDto orderItemDto){
        if (orderService.isExistsByOrderId(orderId)) {
            orderItemService.deleteOrderItem(orderId, orderItemDto.getId());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
