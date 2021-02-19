package ru.javamentor.service.client;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.javamentor.dto.user.ClientDto;
import ru.javamentor.model.user.Client;
import ru.javamentor.model.user.Manager;
import ru.javamentor.repository.user.ClientRepository;

import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SuppressWarnings("deprecation")
public class ClientServiceImplTest {

@Mock
ClientRepository clientRepository;

@InjectMocks
private ClientServiceImpl clientServiceImpl;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }



    @Test
    public void isExistsByClientId() {
        Client client=new Client();
        client.setId(2L);
        when(clientRepository.existsById(client.getId())).thenReturn(true);
        assertTrue(clientServiceImpl.isExistsByClientId(client.getId()));
        verify(clientRepository,times(1)).existsById(client.getId());
    }

    @Test
    public void getClientDtoListFromClientsWithManager() {

        Manager manager=new Manager();
        manager.setId(2L);

        when(clientRepository.getClientDtoListFromClientsWithManager(manager.getId())).thenReturn(Collections.emptyList());
        assertEquals(Collections.emptyList(), clientServiceImpl.getClientDtoListFromClientsWithManager(manager));
        verify(clientRepository,times(1)).getClientDtoListFromClientsWithManager(manager.getId());
    }

    @Test
    public void getClientDtoByClientId() {
        ClientDto clientDto=new ClientDto();
        clientDto.setId(2L);

        when(clientRepository.getClientDtoFromClientWithId(clientDto.getId())).thenReturn(clientDto);
        assertEquals(clientDto, clientServiceImpl.getClientDtoByClientId(clientDto.getId()));
        verify(clientRepository,times(1)).getClientDtoFromClientWithId(clientDto.getId());

    }

    @Test
    public void relationClientWithManager() {
Client client2=new Client();
client2.setId(2l);
Manager manager2=new Manager();
manager2.setId(2l);
when(clientRepository.relationClientWithManager(client2.getId(),manager2.getId())).thenReturn(true);
assertTrue(clientServiceImpl.relationClientWithManager(client2.getId(),manager2.getId()));
verify(clientRepository,times(1)).relationClientWithManager(client2.getId(),manager2.getId());

    }
}