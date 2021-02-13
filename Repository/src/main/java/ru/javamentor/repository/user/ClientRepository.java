package ru.javamentor.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.javamentor.dto.order.ClientDto;
import ru.javamentor.model.user.Client;
import ru.javamentor.model.user.Manager;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("select new ru.javamentor.dto.order.ClientDto(c.id, c.firstName, c.clientName) from " +
            "Client c where c.id = :clientId")
    ClientDto getClientDtoFromClientWithId(@Param("clientId") Long clientId);

    @Query("select new ru.javamentor.dto.order.ClientDto(c.id, c.firstName, c.clientName)"
            + "from Client c where c.manager = :manager")
    List<ClientDto> getClientDtoListFromClientsWithManager(@Param("manager") Manager manager);

    @Query("select count(c.id) > 0  from Client c where c.id = :clientId and c.manager.id = :managerId ")
    Boolean relationClientWithManager(@Param("clientId") Long clientId, @Param("managerId") Long managerId);
}
