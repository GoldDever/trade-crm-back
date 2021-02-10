package ru.javamentor.service.client;

import org.springframework.stereotype.Service;
import ru.javamentor.dto.order.ClientDto;
import ru.javamentor.model.user.Manager;
import ru.javamentor.model.user.Client;
import ru.javamentor.repository.user.ClientRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService{

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
        return clientRepository.getClientDtoListFromClientsWithManager(manager);
    }

    @Override
    public Client getClientById(Long clientId) {
        return null;
    }

    @Override
    public ClientDto getClientDtoByClientId(Long clientId) {
        return clientRepository.getClientDtoFromClientWithId(clientId);
    }
}

