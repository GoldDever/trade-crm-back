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
         createOrderItem("invoiceIssued", 10, 1L, 1L);

         createOrderItem("invoiceIssued", 2, 1L, 1L);
     }

    private void createOrderItem(String invoiceIssued, int productCount, long productId, long orderId) {
        OrderItem orderItem = new OrderItem();
        orderItem.setInvoiceIssued(invoiceIssued);
        orderItem.setProductCount(productCount);
        orderItem.setProduct(productRepository.findById(productId).get());
        orderItem.setOrder(orderRepository.findById(orderId).get());
        orderItemRepository.save(orderItem);
    }
}
