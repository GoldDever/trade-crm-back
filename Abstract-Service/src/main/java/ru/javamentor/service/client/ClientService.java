package ru.javamentor.service.client;

import ru.javamentor.dto.order.ClientDto;
import ru.javamentor.model.user.Client;

public interface ClientService {
    boolean isExistsByClientId(Long orderId);
    Client getClientById(Long clientId);
    ClientDto getClientDtoByClientId(Long clientId);
}
