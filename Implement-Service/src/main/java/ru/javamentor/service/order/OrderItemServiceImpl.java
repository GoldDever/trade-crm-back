package ru.javamentor.service.order;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javamentor.dto.order.OrderItemDto;
import ru.javamentor.model.order.Order;
import ru.javamentor.model.order.OrderApprove;
import ru.javamentor.model.order.OrderItem;
import ru.javamentor.model.product.Product;
import ru.javamentor.repository.order.OrderItemRepository;
import ru.javamentor.repository.order.OrderRepository;
import ru.javamentor.repository.product.ProductRepository;

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
                orderItemDto.getInvoiceIssued(),
                orderItemDto.getProductCount(),
                product,
                order
        );

        orderItemRepository.save(orderItem);
    }

    /**
     * Метод меняет количество товара в Item
     *
     * @param countProduct of product
     * @param orderId id of order
     * @param orderItemId id of orderItem
     */
    @Override
    @Transactional
    public void editOrderItem(Long orderId, Long orderItemId, Integer countProduct) {
        orderItemRepository.setProductCountByOrderItem(orderItemId, countProduct);
    }

    /**
     * Метод удаляет orderItem
     * @param orderId
     * @param orderItemId
     */
    @Override
    @Transactional
    public void deleteOrderItem(Long orderId, Long orderItemId) {
        orderItemRepository.deleteOrderItemById(orderItemId);
    }
}
