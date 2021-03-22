package ru.javamentor.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.javamentor.model.order.OrderApproveAnswer;

@Repository
public interface OrderApproveAnswerRepository extends JpaRepository<OrderApproveAnswer, Long> {
}
