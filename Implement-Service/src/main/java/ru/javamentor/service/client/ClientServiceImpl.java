package ru.javamentor.service.client;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.javamentor.dto.user.ClientDto;
import ru.javamentor.model.user.Client;
import ru.javamentor.model.user.Manager;
import ru.javamentor.repository.user.ClientRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    /**
     * Метод возвращает boolean при проверке существования клиента с данным Id.
     *
     * @param clientId - айди клиента
     * @return - Возвращает boolean, соответствующий результату.
     */
    @Transactional
    @Override
    public boolean isExistsByClientId(Long clientId) {
        return clientRepository.existsById(clientId);
    }

    /**
     * Метод возвращает список клиентов прикрепленных к менеджеру
     *
     * @param manager - Менеджер
     * @return список клиентов
     */
    @Transactional
    @Override
    public List<ClientDto> getClientDtoListFromClientsWithManager(Manager manager) {
        return clientRepository.getClientDtoListFromClientsWithManager(manager.getId());
    }

    /**
     * Метод возвращает ClientDto, клиента по id
     *
     * @param clientId - id клиента
     * @return ClientDto клиента
     */
    @Override
    public ClientDto getClientDtoByClientId(Long clientId) {
        return clientRepository.getClientDtoFromClientWithId(clientId);
    }
    /**
     * Метод возвращает айди менеджера клиента по clientId
     *
     * @param clientId - айди клиента
     * @return айди менедера
     */
    @Transactional
    @Override
    public boolean relationClientWithManager(Long clientId, Long managerId){
        return clientRepository.relationClientWithManager(clientId, managerId);
    }
@Transactional
@Override
public ResponseEntity<String> updateClient (ClientDto clientDto) {
    Long idFromClientDtoForСheck = clientDto.getId();
    if (!clientRepository.existsById(idFromClientDtoForСheck)) {
        return new ResponseEntity<>("Клиент с таким id, не существует", HttpStatus.BAD_REQUEST);
    }
    Client updateClient = clientRepository.findById(idFromClientDtoForСheck).orElse(new Client());
    Manager manager = updateClient.getManager();
    if (manager != null) {
        return new ResponseEntity<>("На данный id, зарегистрирован менеджер" + manager.getLastName() +
                manager.getFirstName(), HttpStatus.BAD_REQUEST);
    } else
        updateClient.setId(clientDto.getId());
        updateClient.setFirstName(clientDto.getFirstName());
        updateClient.setLastName(clientDto.getLastName());
        updateClient.setPatronymic(clientDto.getPatronymic());
        updateClient.setClientName (clientDto.getClientName());
        updateClient.setUsername(clientDto.getEmail());
        clientRepository.save(updateClient);

    return null;
}
    }

