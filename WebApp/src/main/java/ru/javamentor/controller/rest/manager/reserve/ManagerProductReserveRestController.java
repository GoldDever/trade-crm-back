package ru.javamentor.controller.rest.manager.reserve;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javamentor.model.product.ReserveProduct;
import ru.javamentor.service.product.ReserveProductService;

import java.util.List;

@RestController
@RequestMapping("api/manager/productReserve")
public class ManagerProductReserveRestController {

    private ReserveProductService reserveProductService;

    public ManagerProductReserveRestController(ReserveProductService reserveProductService) {
        this.reserveProductService = reserveProductService;
    }

    @GetMapping("/all/{productId}")
    public ResponseEntity<?> getAllReserveProductByProductId(@PathVariable String productId) {
        //TODO Метод принимает productId и возвращает List<ReserveProductDto> которые относятся к данному продукту
        return null;
    }

    @GetMapping("/count/order/{orderId}/product/{productId}")
    public ResponseEntity<?> getCountReservedProductByOrderIdAndProductId(@PathVariable String orderId, @PathVariable String productId) {
        //TODO Метод принимает orderId, productId и возвращает количество товара которое зарезервировано в этом ордере
        return null;
    }

    /**
     * Метод для добавления резерва по orderId
     *
     * @param orderId - id заказа
     * @return - HTTP ответ с BODY
     */
    @GetMapping("/{orderId}/all/addReserve")
    public ResponseEntity<String> addProductReserveForAllProductInOrder(@PathVariable Long orderId) {
        String result = reserveProductService.addReserveByOrder(orderId);
        if (result.isEmpty()) {
            return new ResponseEntity<>("Товар зарезирвирован", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Часть товаров не может быть зарезирвированна: \n" + result, HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * POST метод для резервирования продукта
     *
     * @param orderId      - id Order
     * @param productId    - id продукта по которому сохраняется резерв
     * @param productCount - количество продукта, которое необходимо зарезервировать
     * @return - сообщение о состоянии HTTP-ответа
     */
    @PostMapping("/count/order/{orderId}/product/{productId}/count/{productCount}/addReserve")
    public ResponseEntity<String> addProductReserve(@PathVariable Long orderId,
                                                    @PathVariable Long productId,
                                                    @PathVariable Integer productCount) {
        String response = reserveProductService.saveProductReserve(orderId, productId, productCount);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    /**
     * Метод для удаления
     * зарезирвированного продукта
     *
     * @param orderId      - id заказа
     * @param productId    - id продукта
     * @param productCount - количество удалеямого продукта из резерва
     * @return - HTTP ответ с BODY
     */
    @GetMapping("/count/order/{orderId}/product/{productId}/count/{productCount}/removeReserve")
    public ResponseEntity<String> removeProductReserve(@PathVariable Long orderId,
                                                       @PathVariable Long productId,
                                                       @PathVariable Integer productCount) {
        return new ResponseEntity<>(
                reserveProductService.removeProductReserve(orderId, productId, productCount), HttpStatus.OK);
    }
}
