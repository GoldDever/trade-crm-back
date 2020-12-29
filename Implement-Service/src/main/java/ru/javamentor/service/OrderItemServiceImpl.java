package ru.javamentor.service;

import org.springframework.stereotype.Service;
import ru.javamentor.dto.order.OrderItemDto;
import ru.javamentor.model.order.Order;
import ru.javamentor.model.order.OrderItem;
import ru.javamentor.model.product.Product;
import ru.javamentor.repository.order.OrderItemRepository;
import ru.javamentor.repository.order.OrderRepository;
import ru.javamentor.repository.order.ProductRepository;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderItemServiceImpl(OrderItemRepository orderItemRepository, OrderRepository orderRepository,
                                ProductRepository productRepository) {
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    /**
     * Method of service transform DTO objects to Entities
     * and add order item to DB throw repository
     *
     * @param orderItemDto DTO item and order
     * @param orderId      id of order
     */
    @Override
    public void saveOrderItem(OrderItemDto orderItemDto, String orderId) {
        Order order = orderRepository.findById(Long.valueOf(orderId)).orElseThrow();
        Product product = productRepository.findById(orderItemDto.getProduct().getId()).orElseThrow();
        OrderItem orderItem = new OrderItem(
                orderItemDto.getId(),
                orderItemDto.getIdFromErp(),
                orderItemDto.getInvoiceIssued(),
                orderItemDto.getProductCount(),
                product,
                order,
                orderItemDto.getItemFullPrice()
        );

        orderItemRepository.save(orderItem);
    }
}
