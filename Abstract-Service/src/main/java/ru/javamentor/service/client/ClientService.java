package ru.javamentor.service.client;

import ru.javamentor.dto.order.ClientDto;
import ru.javamentor.model.user.Manager;
import ru.javamentor.model.user.Client;

import java.util.List;

public interface ClientService {
    boolean isExistsByClientId(Long orderId);
    List<ClientDto> getClientDtoListFromClientsWithManager(Manager manager);
    Client getClientById(Long clientId);
    ClientDto getClientDtoByClientId(Long clientId);
}
