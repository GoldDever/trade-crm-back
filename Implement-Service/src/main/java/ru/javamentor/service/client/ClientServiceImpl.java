package ru.javamentor.service.client;


import org.springframework.stereotype.Service;
import ru.javamentor.dto.user.ClientDto;
import ru.javamentor.dto.user.ClientPostDto;
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

    /**
     * Метод обновляет существующего клиента
     *
     * @param clientDto - данные клиента
     */
    @Transactional
    @Override
    public void updateClient(ClientDto clientDto) {
        Client updateClient = clientRepository.findById(clientDto.getId()).get();
        updateClient.setClientName(clientDto.getClientName());
        updateClient.setId(clientDto.getId());
        updateClient.setFirstName(clientDto.getFirstName());
        updateClient.setLastName(clientDto.getLastName());
        updateClient.setPatronymic(clientDto.getPatronymic());
        updateClient.setUsername(clientDto.getEmail());
        clientRepository.save(updateClient);
    }

    /**
     * Метод сохраняет нового клиента
     *
     * @param clientPostDto - данные клиента
     */
    @Transactional
    @Override
    public void saveNewClient(ClientPostDto clientPostDto) {
        Client clientDto = new Client();
        clientDto.setFirstName(clientPostDto.getFirstName());
        clientDto.setLastName(clientPostDto.getLastName());
        clientDto.setClientName(clientPostDto.getClientName());
        clientDto.setPatronymic(clientPostDto.getPatronymic());
        clientDto.setUsername(clientPostDto.getEmail());
        clientRepository.save(clientDto);

    }


    /**
     * Метод возвращает boolean при проверке существования клиента с данным email.
     *
     * @param email - почта клиента
     * @return - Возвращает boolean, соответствующий результату.
     */
    @Transactional
    @Override
    public boolean isExistsClientByEmail(String email) {
        return clientRepository.existsClientByUsername(email);
    }


    /**
     * Метод возвращает имя и фамилию клиента по email
     *
     * @param email - email клиента
     * @return ClientFullName клиента
     */
    @Override
    public String getClientFullNameByEmail(String email) {
        return clientRepository.getClientFullNameByEmail(email);
    }

}

