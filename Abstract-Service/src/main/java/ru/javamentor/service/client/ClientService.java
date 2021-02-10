package ru.javamentor.service.client;

import ru.javamentor.dto.order.ClientDto;
import ru.javamentor.model.user.Manager;


import java.util.List;

public interface ClientService {
    boolean isExistsByClientId(Long orderId);
    boolean isExistsByManagerAndClientId(Manager manager, Long clientId);
    List<ClientDto> getClientDtoListFromClientsWithManager(Manager manager);
    ClientDto getClientDtoByClientId(Long clientId);
}
