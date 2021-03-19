package ru.javamentor.service.order;

import org.springframework.stereotype.Service;
import ru.javamentor.dto.order.OrderApproveAnswerDto;
import ru.javamentor.model.order.OrderApproveAnswer;
import ru.javamentor.model.order.OrderApproveRequest;
import ru.javamentor.repository.order.OrderApproveAnswerRepository;
import ru.javamentor.repository.order.OrderApproveRequestRepository;

@Service
public class OrderApproveAnswerServiceImpl implements OrderApproveAnswerService {
    private final OrderApproveAnswerRepository orderApproveAnswerRepository;
    private final OrderApproveRequestRepository orderApproveRequestRepository;

    public OrderApproveAnswerServiceImpl(OrderApproveAnswerRepository orderApproveAnswerRepository, OrderApproveRequestRepository orderApproveRequestRepository) {
        this.orderApproveAnswerRepository = orderApproveAnswerRepository;
        this.orderApproveRequestRepository = orderApproveRequestRepository;
    }

    @Override
    public void saveOrderApproveAnswer(OrderApproveAnswerDto orderApproveAnswerDto) {
        OrderApproveRequest orderApproveRequest = orderApproveRequestRepository.findById(orderApproveAnswerDto.getOrderApproveRequest().getId()).orElseThrow();
        OrderApproveAnswer orderApproveAnswer = new OrderApproveAnswer(orderApproveAnswerDto.isApprove(), orderApproveAnswerDto.getText(), orderApproveRequest);
        orderApproveAnswerRepository.save(orderApproveAnswer);
    }
}
