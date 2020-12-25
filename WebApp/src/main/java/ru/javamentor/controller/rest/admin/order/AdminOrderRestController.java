package ru.javamentor.controller.rest.admin.order;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javamentor.service.OrderService;

@RestController
@RequestMapping("api/admin/order")
public class AdminOrderRestController {

    private final OrderService orderService;

    public AdminOrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }
}
