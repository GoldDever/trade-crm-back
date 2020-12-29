package ru.javamentor.service;

import org.springframework.stereotype.Service;
import ru.javamentor.dto.order.OrderItemDto;
import ru.javamentor.model.order.Order;
import ru.javamentor.model.order.OrderItem;
import ru.javamentor.model.product.Product;
import ru.javamentor.model.product.Supplier;
import ru.javamentor.model.product.Unit;
import ru.javamentor.repository.order.OrderItemRepository;
import ru.javamentor.repository.order.OrderRepository;
import ru.javamentor.repository.order.SupplierRepository;
import ru.javamentor.repository.order.UnitRepository;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final SupplierRepository supplierRepository;
    private final UnitRepository unitRepository;

    public OrderItemServiceImpl(OrderItemRepository orderItemRepository, OrderRepository orderRepository,
                                SupplierRepository supplierRepository, UnitRepository unitRepository) {
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
        this.supplierRepository = supplierRepository;
        this.unitRepository = unitRepository;
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

        Supplier supplier = supplierRepository.findById(orderItemDto.getProduct().getSupplierId()).orElseThrow();
        Set<Supplier> suppliers = new HashSet<>();
        suppliers.add(supplier);
        Unit unit = unitRepository.findById(orderItemDto.getProduct().getUnitId()).orElseThrow();
        Product product = new Product(
                orderItemDto.getProduct().getId(),
                orderItemDto.getProduct().getProductName(),
                orderItemDto.getProduct().getMadeCountry(),
                suppliers,
                orderItemDto.getProduct().getArticle(),
                BigDecimal.valueOf(orderItemDto.getProduct().getPurchasePrice()),
                BigDecimal.valueOf(orderItemDto.getProduct().getPrice()),
                BigDecimal.valueOf(orderItemDto.getProduct().getMargin()),
                unit,
                orderItemDto.getProduct().getPackagingCount()
        );

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
