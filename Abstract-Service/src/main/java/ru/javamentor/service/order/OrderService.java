package ru.javamentor.service.order;

import ru.javamentor.dto.order.OrderApproveDto;
import ru.javamentor.model.user.User;

public interface OrderService {
    void updateApproveStatus(OrderApproveDto orderApproveDto, Long orderId);
    void newOrder(Long clientId, User user);
}
