package ru.javamentor.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javamentor.model.user.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
}
