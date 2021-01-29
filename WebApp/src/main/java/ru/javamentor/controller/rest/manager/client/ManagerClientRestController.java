package ru.javamentor.controller.rest.manager.client;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javamentor.service.client.ClientService;
import ru.javamentor.service.order.OrderService;

@RestController
@RequestMapping("api/manager/client")
public class ManagerClientRestController {

    private final ClientService clientService;
    private final OrderService orderService;

    public ManagerClientRestController(ClientService clientService, OrderService orderService) {
        this.clientService = clientService;
        this.orderService = orderService;
    }

    /**
     * GET метод для получения списка заказов клиента на странице менеджера
     *
     * @param clientId - айди клиента
     * @return - список заказов клиента
     */

    @GetMapping("/{clientId}/allOrders")
    public ResponseEntity<?> getAllClientOrders(@PathVariable Long clientId) {

        if (clientService.isExistsByClientId(clientId)) {
            return new ResponseEntity<>(orderService.getOrderDtoListByClientId(clientId), HttpStatus.OK);
        }
        return ResponseEntity.badRequest().body("Нет клиента с Id - " + clientId);
    }
}
