package ru.javamentor.init.order;

import org.springframework.stereotype.Component;
import ru.javamentor.model.order.OrderItem;
import ru.javamentor.repository.order.OrderItemRepository;
import ru.javamentor.repository.order.OrderRepository;
import ru.javamentor.repository.product.ProductRepository;

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
        OrderItem orderItem1 = new OrderItem();
        orderItem1.setInvoiceIssued("invoiceIssued");
        orderItem1.setProductCount(10);
        orderItem1.setProduct(productRepository.findById(1L).get());
        orderItem1.setOrder(orderRepository.findById(1L).get());
        orderItem1.setPosition(1);
        orderItemRepository.save(orderItem1);

        OrderItem orderItem2 = new OrderItem();
        orderItem2.setInvoiceIssued("invoiceIssued");
        orderItem2.setProductCount(2);
        orderItem2.setProduct(productRepository.findById(1L).get());
        orderItem2.setOrder(orderRepository.findById(1L).get());
        orderItem2.setPosition(2);
        orderItemRepository.save(orderItem2);
    }
}
