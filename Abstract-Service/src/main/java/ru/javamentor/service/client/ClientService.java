package ru.javamentor.service.client;

import ru.javamentor.dto.order.ClientDto;
import ru.javamentor.model.user.Manager;

import java.util.List;

public interface ClientService {

    boolean isExistsByClientId(Long orderId);
    List<ClientDto> getClientDtoListFromClientsWithManager(Manager manager);

    boolean relationClientWithManager(Long clientId, Long managerId);
}
