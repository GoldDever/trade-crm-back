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

import java.util.List;

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
    public void saveOrderItem(Long orderId, OrderItemDto orderItemDto) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        Product product = productRepository.findById(orderItemDto.getProduct().getId()).orElseThrow();
        Integer lastPosition = orderItemRepository.getNumberOfPositionInOrder(order.getId());
        OrderItem orderItem = new OrderItem(
                orderItemDto.getId(),
                orderItemDto.getInvoiceIssued(),
                orderItemDto.getProductCount(),
                product,
                order,
                lastPosition + 1,
                orderItemDto.getCurrentMargePercent()
        );

        orderItemRepository.save(orderItem);
    }


    /**
     * Метод меняет количество товара в Item
     *
     * @param countProduct of product
     * @param orderItemId  id of orderItem
     */
    @Override
    @Transactional
    public void editOrderItem(Long orderItemId, Integer countProduct) {
        orderItemRepository.setProductCountByOrderItem(orderItemId, countProduct);
    }

    /**
     * Метод удаляет orderItem
     * @param orderItemId
     */
    @Override
    @Transactional
    public void deleteOrderItem(Long orderItemId) {
        OrderItem orderItem = orderItemRepository.getOne(orderItemId);
        Integer deletedPosition = orderItem.getPosition();
        Long orderId = orderItem.getOrder().getId();
        orderItemRepository.deleteOrderItemById(orderItemId);
        updatePositions(orderId, deletedPosition);
    }

    /**
     * Метод получает OrderItem из базы по id OrderItemDTO
     *
     * @param orderItemDto
     * @return orderItem
     */
    @Override
    public OrderItem getOrderItemByDTO(OrderItemDto orderItemDto) {
        return orderItemRepository.getOrderItemByDtoID(orderItemDto.getId());
    }

    /**
     * Метод обновляет orderItem.position после удаления
     *
     * @param orderId
     * @param deletedPosition
     */
    public void updatePositions(Long orderId, Integer deletedPosition) {
        List<OrderItem> orderItemList = orderItemRepository.getListOrderItemByOrderId(orderId);
        for (OrderItem orderItem : orderItemList) {
            Integer currentPosition = orderItem.getPosition();
            if (currentPosition > deletedPosition) {
                orderItemRepository.updateOrderItemPosition(orderItem.getId(), --currentPosition);
            }
        }
    }

}
