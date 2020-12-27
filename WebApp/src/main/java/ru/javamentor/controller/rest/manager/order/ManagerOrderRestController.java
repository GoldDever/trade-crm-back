package ru.javamentor.controller.rest.manager.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javamentor.dto.order.AddItemDto;
import ru.javamentor.dto.order.OrderDto;
import ru.javamentor.dto.order.OrderItemDto;
import ru.javamentor.model.Client;
import ru.javamentor.model.Manager;
import ru.javamentor.model.Order;
import ru.javamentor.model.OrderItem;
import ru.javamentor.model.product.Product;
import ru.javamentor.model.product.Supplier;
import ru.javamentor.model.product.Unit;
import ru.javamentor.service.OrderItemService;
import ru.javamentor.service.OrderService;
import ru.javamentor.service.SupplierService;
import ru.javamentor.service.UnitService;
import ru.javamentor.service.UserService;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;


@RestController
@RequestMapping("api/manager/order")
public class ManagerOrderRestController {

    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;

    @Autowired
    OrderItemService orderItemService;

    @Autowired
    SupplierService supplierService;

    @Autowired
    UnitService unitService;

    @PostMapping(value = "/{orderId}/addItem", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addItem(@RequestBody AddItemDto addItemDto,
                                     @PathVariable String orderId) {

        OrderDto orderDto = addItemDto.getOrderDto();
        Client client = (Client) userService.getUserById(orderDto.getClient().getId());
        Manager manager = (Manager) userService.getUserById(orderDto.getManager().getId());
        Order order = orderService.getOrderById(Long.valueOf(orderId));
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        order.setId(orderDto.getId());
        order.setIdFromErp(orderDto.getIdFromErp());
        order.setClient(client);
        order.setManager(manager);
        order.setOrderFullPrice(orderDto.getOrderFullPrice());
        order.setApproved(orderDto.getApproved());
        order.setPaid(orderDto.getPaid());
        order.setShipped(orderDto.getShipped());
        order.setCreateTime(orderDto.getCreateTime());

        OrderItemDto orderItemDto = addItemDto.getOrderItemDto();
        Set<Supplier> suppliers = new HashSet<>();
        Supplier supplier = supplierService.getSupplierById(orderItemDto.getProduct().getSupplierId());
        suppliers.add(supplier);
        Unit unit = unitService.getUnitById(orderItemDto.getProduct().getUnitId());
        Product product = new Product();
        product.setId(orderItemDto.getProduct().getId());
        product.setProductName(orderItemDto.getProduct().getProductName());
        product.setMadeCountry(orderItemDto.getProduct().getMadeCountry());
        product.setSuppliers(suppliers);
        product.setArticle(orderItemDto.getProduct().getArticle());
        product.setPurchasePrice(BigDecimal.valueOf(orderItemDto.getProduct().getPurchasePrice()));
        product.setPrice(BigDecimal.valueOf(orderItemDto.getProduct().getPrice()));
        product.setMargin(BigDecimal.valueOf(orderItemDto.getProduct().getMargin()));
        product.setUnit(unit);
        product.setPackagingCount(orderItemDto.getProduct().getPackagingCount());
        OrderItem orderItem = new OrderItem(
                orderItemDto.getIdFromErp(),
                orderItemDto.getInvoiceIssued(),
                orderItemDto.getProductCount(),
                product,
                orderItemDto.getItemFullPrice()
        );

        orderService.saveOrder(order);
        orderItemService.saveOrderItem(orderItem);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
