package ru.javamentor.service.order;

import org.springframework.stereotype.Service;
import ru.javamentor.dto.order.ClientDto;
import ru.javamentor.dto.order.ManagerDto;
import ru.javamentor.dto.order.OrderApproveDto;
import ru.javamentor.dto.order.OrderDto;
import ru.javamentor.dto.order.OrderItemDto;
import ru.javamentor.model.order.Order;
import ru.javamentor.model.order.OrderApprove;
import ru.javamentor.model.user.User;
import ru.javamentor.repository.order.OrderApproveRepository;
import ru.javamentor.repository.order.OrderItemRepository;
import ru.javamentor.repository.order.OrderRepository;
import ru.javamentor.repository.product.ReserveProductRepository;
import ru.javamentor.repository.user.ClientRepository;
import ru.javamentor.repository.user.ManagerRepository;
import ru.javamentor.service.product.ProductService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderApproveRepository orderApproveRepository;
    private final OrderItemRepository orderItemRepository;
    private final ClientRepository clientRepository;
    private final ManagerRepository managerRepository;
    private final ReserveProductRepository reserveProductRepository;
    private final ProductService productService;


    public OrderServiceImpl(OrderRepository orderRepository,
                            OrderApproveRepository orderApproveRepository,
                            OrderItemRepository orderItemRepository,
                            ClientRepository clientRepository,
                            ManagerRepository managerRepository,
                            ReserveProductRepository reserveProductRepository,
                            ProductService productService) {
        this.orderRepository = orderRepository;
        this.orderApproveRepository = orderApproveRepository;
        this.orderItemRepository = orderItemRepository;
        this.clientRepository = clientRepository;
        this.managerRepository = managerRepository;
        this.reserveProductRepository = reserveProductRepository;
        this.productService = productService;
    }

    /**
     * Метод меняет флаг в Order на тот, что пришёл в orderApproveDto
     * сохраняет новый OrderApprove
     *
     * @param orderApproveDto - ДТО из которого получаем новый флаг isApprove
     * @param orderId - id по которому находим Order и изменяем у него флаг isApprove
     */
    @Override
    public void updateApproveStatus(OrderApproveDto orderApproveDto, Long orderId) {
        Order order = orderRepository.findById(orderId).get();
        OrderApprove orderApprove = new OrderApprove(orderApproveDto.isApprove(), orderApproveDto.getText(), order);

        order.setApprove(orderApproveDto.isApprove());
        orderRepository.save(order);
        orderApproveRepository.save(orderApprove);
    }

    /**
     * Метод изменяет флаг на true.
     * И удаляет все резервы связанные с этим заказом.
     *
     * @param orderIdFromErp - идентификатор заказа из ERP системы
     */
    @Transactional
    @Override
    public Long updateShippedStatus(String orderIdFromErp) {
        Long orderId = orderRepository.getOrderIdByIdFromErp(orderIdFromErp);
        orderRepository.updateOrderShippedStatus(orderId);

        reserveProductRepository.deleteByOrderId(orderId);

        return orderId;
    }

    /**
     * Метод сохраняет новый Order
     *
     * @param clientId - id клиента
     * @param user - user из principal для получения manager
     */
    @Transactional
    @Override
    public void newOrder(Long clientId, User user) {
        Order order = new Order(
                clientRepository.findById(clientId).orElseThrow(),
                managerRepository.findById(user.getId()).orElseThrow()
        );
        orderRepository.save(order);
    }

    /**
     * Метод инициализирующий orderDTO ордером из Базы Данных
     *
     * @param orderId - ID ордера
     * @return - Возвращает orderDTO
     */
    @Override
    public OrderDto getOrderDtoByOrderId(Long orderId) {
        OrderDto orderDto = orderRepository.getOrderDtoWithOrderId(orderId);
        Long clientId = orderRepository.getClientIdByOrderId(orderId);
        Long managerId = orderRepository.getManagerIdByOrderId(orderId);
        ClientDto clientDto = clientRepository.getClientDtoFromClientWithId(clientId);
        ManagerDto managerDto = managerRepository.getManagerDtoById(managerId);
        List<OrderItemDto> orderItemDtoList = orderItemRepository.getListOrderItemDtoByOrderId(orderId);
        orderItemDtoList.forEach(orderItemDto -> orderItemDto.setProduct(
                productService.getProductDtoByProductId(
                        orderItemRepository.findProductIdByOrderItemId(orderItemDto.getId()))));
        orderDto.setClient(clientDto);
        orderDto.setManager(managerDto);
        orderDto.setOrderItemList(orderItemDtoList);
        return orderDto;
    }

    /**
     * Метод, возвращающий boolean при проверке существования ордера с данным Id.
     *
     * @param orderId - Принимает Id ордера как аргумент.
     * @return - Возвращает boolean, соответствующий результату.
     */
    @Override
    public boolean isExistsByOrderId(Long orderId) {
        return orderRepository.existsById(orderId);
    }

    /**
     * Метод, возвращающий список ордеров клиента с clientId.
     *
     * @param clientId - Принимает Id клиента как аргумент.
     * @return - Возвращает список ордеров клиента.
     */
    @Override
    public List<OrderDto> getOrderDtoListByClientId(Long clientId) {
        List<OrderDto> orderDtoList = orderRepository.getOrderDtoListWithClientId(clientId);
        orderDtoList.forEach(orderDto -> {
            List<OrderItemDto> orderItemDtoList = orderItemRepository.getListOrderItemDtoByOrderId(orderDto.getId());
            orderItemDtoList.forEach(orderItemDto -> orderItemDto.setProduct(
                    productService.getProductDtoByProductId(
                            orderItemRepository.findProductIdByOrderItemId(orderItemDto.getId()))));
            orderDto.setOrderItemList(orderItemDtoList);
        });
        return orderDtoList;
    }

    /**
     * Метод, возвращающий список ордеров менеджера с managerId.
     *
     * @param managerId - Принимает Id менеджера как аргумент.
     * @return - Возвращает список ордеров менеджера.
     */
    @Override
    public List<OrderDto> getAllOrderDtoListByManagerId(Long managerId) {
        List<OrderDto> allOrderDtoList = orderRepository.getAllOrderDtoListByManagerId(managerId);
        allOrderDtoList.forEach(orderDto -> {
            List<OrderItemDto> orderItemDtoList = orderItemRepository.getListOrderItemDtoByOrderId(orderDto.getId());
            orderItemDtoList.forEach(orderItemDto -> orderItemDto.setProduct(
                    productService.getProductDtoByProductId(
                            orderItemRepository.findProductIdByOrderItemId(orderItemDto.getId()))));
            orderDto.setOrderItemList(orderItemDtoList);
        });
        return allOrderDtoList;
    }
}

