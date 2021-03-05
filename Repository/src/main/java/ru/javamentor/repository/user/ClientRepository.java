package ru.javamentor.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.javamentor.dto.user.ClientDto;
import ru.javamentor.model.user.Client;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("select new ru.javamentor.dto.user.ClientDto(c.id, c.firstName, c.lastName, c.patronymic, c.username, c.clientName) from " +
            "Client c where c.id = :clientId")
    ClientDto getClientDtoFromClientWithId(@Param("clientId") Long clientId);

    @Query("select new ru.javamentor.dto.user.ClientDto(c.id, c.firstName, c.lastName, c.patronymic, c.username, c.clientName)"
            + "from Client c where c.manager.id = :managerId")
    List<ClientDto> getClientDtoListFromClientsWithManager(Long managerId);

    @Query("select count(c.id) > 0  from Client c where c.id = :clientId and c.manager.id = :managerId ")
    Boolean relationClientWithManager(@Param("clientId") Long clientId, @Param("managerId") Long managerId);

    @Query("select new ru.javamentor.dto.user.ClientDto(c.firstName, c.lastName, c.patronymic, c.username, c.clientName) from " +
            "Client c where c.username =:clientEmail")
    ClientDto getClientDtoByEmail(@Param("clientEmail") String email);

    boolean existsClientByUsername(String username);
}
