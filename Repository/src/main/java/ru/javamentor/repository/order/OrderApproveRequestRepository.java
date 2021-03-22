package ru.javamentor.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.javamentor.model.order.OrderApproveRequest;

@Repository
public interface OrderApproveRequestRepository extends JpaRepository<OrderApproveRequest, Long> {
}
