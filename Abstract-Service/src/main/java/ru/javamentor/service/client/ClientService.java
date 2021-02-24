package ru.javamentor.service.client;

import ru.javamentor.dto.user.ClientDto;
import ru.javamentor.model.user.Client;
import ru.javamentor.model.user.Manager;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    boolean isExistsByClientId(Long orderId);
    List<ClientDto> getClientDtoListFromClientsWithManager(Manager manager);
    ClientDto getClientDtoByClientId(Long clientId);
    boolean relationClientWithManager(Long clientId, Long managerId);
    void updateClient(ClientDto clientDto);

}
