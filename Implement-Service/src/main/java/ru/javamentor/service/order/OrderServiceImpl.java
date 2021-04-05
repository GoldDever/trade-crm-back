package ru.javamentor.service.order;

import org.springframework.stereotype.Service;
import ru.javamentor.dto.order.OrderApproveAnswerDto;
import ru.javamentor.dto.user.ClientDto;
import ru.javamentor.dto.user.ManagerDto;
import ru.javamentor.dto.order.OrderDto;
import ru.javamentor.dto.order.OrderItemDto;
import ru.javamentor.model.order.Order;
import ru.javamentor.model.order.OrderApproveAnswer;
import ru.javamentor.model.order.OrderApproveRequest;
import ru.javamentor.model.user.User;
import ru.javamentor.repository.order.OrderApproveAnswerRepository;
import ru.javamentor.repository.order.OrderApproveRequestRepository;
import ru.javamentor.repository.order.OrderItemRepository;
import ru.javamentor.repository.order.OrderRepository;
import ru.javamentor.repository.product.ReserveProductRepository;
import ru.javamentor.repository.user.ClientRepository;
import ru.javamentor.repository.user.ManagerRepository;
import ru.javamentor.service.product.ProductService;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service

public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderApproveRequestRepository orderApproveRequestRepository;
    private final OrderItemRepository orderItemRepository;
    private final ClientRepository clientRepository;
    private final ManagerRepository managerRepository;
    private final ReserveProductRepository reserveProductRepository;
    private final ProductService productService;
    private final OrderApproveAnswerRepository orderApproveAnswerRepository;


    public OrderServiceImpl(OrderRepository orderRepository,
                            OrderApproveRequestRepository orderApproveRequestRepository,
                            OrderItemRepository orderItemRepository,
                            ClientRepository clientRepository,
                            ManagerRepository managerRepository,
                            ReserveProductRepository reserveProductRepository,
                            ProductService productService, OrderApproveAnswerRepository orderApproveAnswerRepository) {
        this.orderRepository = orderRepository;
        this.orderApproveRequestRepository = orderApproveRequestRepository;
        this.orderItemRepository = orderItemRepository;
        this.clientRepository = clientRepository;
        this.managerRepository = managerRepository;
        this.reserveProductRepository = reserveProductRepository;
        this.productService = productService;
        this.orderApproveAnswerRepository = orderApproveAnswerRepository;
    }

    /**
     * Метод меняет флаг в Order на тот, что пришёл в OrderApproveAnswerDto
     * сохраняет новый OrderApproveAnswer
     *
     * @param orderApproveAnswerDto - ДТО из которого получаем новый флаг isApprove
     * @param orderId         - id по которому находим Order и изменяем у него флаг isApprove
     */
    @Override
    public void updateApproveStatus(OrderApproveAnswerDto orderApproveAnswerDto, Long orderId) {

        Order order = orderRepository.findById(orderId).orElseThrow();
        OrderApproveRequest orderApproveRequest = orderApproveRequestRepository.findById(orderApproveAnswerDto.getOrderApproveRequest().getId()).orElseThrow();
        OrderApproveAnswer orderApproveAnswer = new OrderApproveAnswer(orderApproveAnswerDto.isApprove(), orderApproveAnswerDto.getText(), orderApproveRequest);

        order.setApprove(orderApproveAnswer.isApprove());
        orderRepository.save(order);
        orderApproveAnswerRepository.save(orderApproveAnswer);
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
     * @param user     - user из principal для получения manager
     * @return - id созданного заказа
     */
    @Transactional
    @Override
    public Long newOrder(Long clientId, User user) {
        Order order = new Order();

        if (clientId != null) {
            order.setClient(clientRepository.findById(clientId).orElseThrow());
        }

        order.setManager(managerRepository.findById(user.getId()).orElseThrow());
        order.setApprove(true);
        order.setPaid(false);
        order.setShipped(false);
        order.setCreateTime(LocalDateTime.now());

        orderRepository.save(order);
        return order.getId();
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

            Long managerId = orderRepository.getManagerIdByOrderId(orderDto.getId());
            ClientDto clientDto = clientRepository.getClientDtoFromClientWithId(clientId);
            ManagerDto managerDto = managerRepository.getManagerDtoById(managerId);
            orderDto.setClient(clientDto);
            orderDto.setManager(managerDto);
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

            Long clientId = orderRepository.getClientIdByOrderId(orderDto.getId());
            ClientDto clientDto = clientRepository.getClientDtoFromClientWithId(clientId);
            ManagerDto managerDto = managerRepository.getManagerDtoById(managerId);
            orderDto.setClient(clientDto);
            orderDto.setManager(managerDto);
        });
        return allOrderDtoList;
    }
}

