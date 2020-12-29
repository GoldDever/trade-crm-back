package ru.javamentor.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javamentor.model.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
}
