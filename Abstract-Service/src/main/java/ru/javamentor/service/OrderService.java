package ru.javamentor.service;

import ru.javamentor.dto.order.OrderApproveDto;

public interface OrderService {
    void updateApproveStatus(OrderApproveDto orderApproveDto, Long orderId);
}
