package ru.javamentor.service;

import org.springframework.stereotype.Service;
import ru.javamentor.dto.order.OrderItemDto;
import ru.javamentor.model.Client;
import ru.javamentor.model.Manager;
import ru.javamentor.model.Order;
import ru.javamentor.model.OrderItem;
import ru.javamentor.model.product.Product;
import ru.javamentor.model.product.Supplier;
import ru.javamentor.model.product.Unit;
import ru.javamentor.repository.order.ClientRepository;
import ru.javamentor.repository.order.ManagerRepository;
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
    private final ClientRepository clientRepository;
    private final ManagerRepository managerRepository;
    private final OrderRepository orderRepository;
    private final SupplierRepository supplierRepository;
    private final UnitRepository unitRepository;

    public OrderItemServiceImpl(OrderItemRepository orderItemRepository, ClientRepository clientRepository,
                                ManagerRepository managerRepository, OrderRepository orderRepository,
                                SupplierRepository supplierRepository, UnitRepository unitRepository) {
        this.orderItemRepository = orderItemRepository;
        this.clientRepository = clientRepository;
        this.managerRepository = managerRepository;
        this.orderRepository = orderRepository;
        this.supplierRepository = supplierRepository;
        this.unitRepository = unitRepository;
    }

    /**
     * Method of service transform DTO objects to Entities
     * and add order item to DB throw repository
     *
     * @param orderItemDto DTO item and order
     * @param orderId id of order
     */
    @Override
    public void saveOrderItem(OrderItemDto orderItemDto, String orderId) {
        Client client = clientRepository.findById(orderItemDto.getOrderDto().getClient().getId()).orElseThrow();
        Manager manager = managerRepository.findById(orderItemDto.getOrderDto().getManager().getId()).orElseThrow();
        Order order = orderRepository.findById(Long.valueOf(orderId)).orElseThrow();
        order.setId(orderItemDto.getOrderDto().getId());
        order.setIdFromErp(orderItemDto.getOrderDto().getIdFromErp());
        order.setClient(client);
        order.setManager(manager);
        order.setOrderFullPrice(orderItemDto.getOrderDto().getOrderFullPrice());
        order.setApprove(orderItemDto.getOrderDto().getApproved());
        order.setPaid(orderItemDto.getOrderDto().getPaid());
        order.setShipped(orderItemDto.getOrderDto().getShipped());
        order.setCreateTime(orderItemDto.getOrderDto().getCreateTime());

        Supplier supplier = supplierRepository.findById(orderItemDto.getProduct().getSupplierId()).orElseThrow();
        Set<Supplier> suppliers = new HashSet<>();
        suppliers.add(supplier);
        Unit unit = unitRepository.findById(orderItemDto.getProduct().getUnitId()).orElseThrow();
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
