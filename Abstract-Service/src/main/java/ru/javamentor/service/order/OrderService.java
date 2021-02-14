package ru.javamentor.service.order;

import ru.javamentor.dto.order.OrderApproveDto;
import ru.javamentor.dto.order.OrderDto;
import ru.javamentor.model.user.User;

import java.util.List;

public interface OrderService {
    void updateApproveStatus(OrderApproveDto orderApproveDto, Long orderId);

    Long updateShippedStatus(String orderIdFromErp);

    void newOrder(Long clientId, User user);

    OrderDto getOrderDtoByOrderId(Long orderId);

    boolean isExistsByOrderId(Long orderId);

    List<OrderDto> getOrderDtoListByClientId(Long clientId);

    List<OrderDto> getAllOrderDtoListByManagerId(Long managerId);
}
