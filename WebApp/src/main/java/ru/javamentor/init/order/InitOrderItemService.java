package ru.javamentor.init.order;

import org.springframework.stereotype.Component;
import ru.javamentor.model.order.OrderItem;
import ru.javamentor.repository.order.OrderItemRepository;
import ru.javamentor.repository.order.OrderRepository;
import ru.javamentor.repository.product.ProductRepository;

import java.math.BigDecimal;

@Component
public class InitOrderItemService {


    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;


    public InitOrderItemService(ProductRepository productRepository, OrderItemRepository orderItemRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
    }

    public void initOrderItem() {
        createOrderItem("invoiceIssued", 10, 1L, 1L, BigDecimal.valueOf(18.0), 1);

        createOrderItem("invoiceIssued", 2, 1L, 1L, BigDecimal.valueOf(18.0) , 2);


        createOrderItem("invoiceIssued", 33, 11L, 2L, BigDecimal.valueOf(18.0), 1);

        createOrderItem("invoiceIssued", 16, 12L, 2L, BigDecimal.valueOf(18.0) , 2);

        createOrderItem("invoiceIssued", 10, 1L, 3L, BigDecimal.valueOf(18.0), 1);

        createOrderItem("invoiceIssued", 2, 1L, 3L, BigDecimal.valueOf(18.0) , 2);
    }

    private void createOrderItem(String invoiceIssued, int productCount, long productId, long orderId, BigDecimal currentMargePercent, int position) {
        OrderItem orderItem = new OrderItem();
        orderItem.setInvoiceIssued(invoiceIssued);
        orderItem.setProductCount(productCount);
        orderItem.setProduct(productRepository.findById(productId).get());
        orderItem.setOrder(orderRepository.findById(orderId).get());
        orderItem.setCurrentMargePercent(currentMargePercent);
        orderItem.setPosition(position);
        orderItemRepository.save(orderItem);
    }
}
