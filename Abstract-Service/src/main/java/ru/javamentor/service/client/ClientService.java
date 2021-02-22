package ru.javamentor.service.client;

import org.springframework.http.ResponseEntity;
import ru.javamentor.dto.user.ClientDto;
import ru.javamentor.model.user.Manager;

import java.util.List;

public interface ClientService {

    boolean isExistsByClientId(Long orderId);
    List<ClientDto> getClientDtoListFromClientsWithManager(Manager manager);
    ClientDto getClientDtoByClientId(Long clientId);
    boolean relationClientWithManager(Long clientId, Long managerId);
    ResponseEntity<String> updateClient(ClientDto clientDto);
}
