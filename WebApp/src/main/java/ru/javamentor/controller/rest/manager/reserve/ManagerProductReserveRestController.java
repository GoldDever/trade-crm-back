package ru.javamentor.controller.rest.manager.reserve;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/manager/productReserve")
public class ManagerProductReserveRestController {

    @GetMapping("/all/{productId}")
    public ResponseEntity<?> getAllReserveProductByProductId(@PathVariable String productId) {
        //TODO Метод принимает productId и возвращает список ReserveProductDto которые относятся к данному продукту
        return null;
    }




}
