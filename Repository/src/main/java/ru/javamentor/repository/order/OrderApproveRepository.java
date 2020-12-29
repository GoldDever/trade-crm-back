package ru.javamentor.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.javamentor.model.OrderApprove;

@Repository
public interface OrderApproveRepository extends JpaRepository<OrderApprove, Long> {
}
