package ru.javamentor.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javamentor.model.user.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
