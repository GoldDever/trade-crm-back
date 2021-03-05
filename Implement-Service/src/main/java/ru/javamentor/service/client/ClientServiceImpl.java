package ru.javamentor.service.client;

import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;


    public ClientServiceImpl(ClientRepository clientRepository, PasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;

        this.passwordEncoder = passwordEncoder;
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
        clientDto.setPassword(passwordEncoder.encode(clientPostDto.getPassword()));
        clientDto.setClientName(clientPostDto.getClientName());
        clientDto.setPatronymic(clientPostDto.getPatronymic());
        clientDto.setUsername(clientPostDto.getEmail());
        clientRepository.save(clientDto);

    }

    /**
     * Метод возвращает ClientDto, клиента по email
     *
     * @param email - email клиента
     * @return ClientDto клиента
     */
    @Transactional
    @Override
    public ClientDto getClientDtoByClientEmail(String email) {
        return clientRepository.getClientDtoByEmail(email);
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

}

