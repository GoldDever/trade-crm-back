package ru.javamentor.service.order;

import ru.javamentor.dto.order.OrderApproveAnswerDto;
import ru.javamentor.dto.order.OrderDto;
import ru.javamentor.model.user.User;

import java.util.List;

public interface OrderService {
    void updateApproveStatus(OrderApproveAnswerDto orderApproveAnswerDto, Long orderId);

    Long updateShippedStatus(String orderIdFromErp);

    Long newOrder(Long clientId, User user);

    void deleteOrder(Long orderId);
    OrderDto getOrderDtoByOrderId(Long orderId);

    void updateOrderClient(Long orderId,Long clientId);

    boolean isExistsByOrderId(Long orderId);

    List<OrderDto> getOrderDtoListByClientId(Long clientId);

    List<OrderDto> getAllOrderDtoListByManagerId(Long managerId);
}
