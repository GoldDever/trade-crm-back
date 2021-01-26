package ru.javamentor.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.javamentor.dto.order.ClientDto;
import ru.javamentor.model.user.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("select new ru.javamentor.dto.order.ClientDto(c.id, c.firstName) from " +
            "Client c where c.id = :clientId")
    ClientDto getClientDtoById(@Param("clientId") Long clientId);
}
