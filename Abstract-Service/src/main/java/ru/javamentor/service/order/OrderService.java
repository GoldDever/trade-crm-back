package ru.javamentor.service.order;

import ru.javamentor.dto.order.OrderApproveAnswerDto;
import ru.javamentor.dto.order.OrderDto;
import ru.javamentor.dto.order.OrderItemDto;
import ru.javamentor.model.user.User;

import java.util.List;

public interface OrderService {
    void updateApproveStatus(OrderApproveAnswerDto orderApproveAnswerDto, Long orderId);

    Long updateShippedStatus(String orderIdFromErp);

    Long newOrder(Long clientId, User user);

    OrderDto getOrderDtoByOrderId(Long orderId);

    boolean isExistsByOrderId(Long orderId);

    List<OrderDto> getOrderDtoListByClientId(Long clientId);

    List<OrderDto> getAllOrderDtoListByManagerId(Long managerId);

    void updateOrderDto(OrderDto orderDto);
}
