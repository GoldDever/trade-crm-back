package ru.javamentor.service.order;

import ru.javamentor.dto.order.OrderApproveDto;

public interface OrderService {
    void updateApproveStatus(OrderApproveDto orderApproveDto, Long orderId);
}
