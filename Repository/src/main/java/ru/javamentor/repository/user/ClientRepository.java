package ru.javamentor.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javamentor.model.user.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
