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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ClientServiceImplTest {

@Mock
ClientRepository clientRepository;

@InjectMocks
private ClientServiceImpl clientServiceImpl;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }
    Client testClient1=new Client();
    Client testClient2=new Client();
    Client testClient3=new Client();
    ClientDto testClientDto1=new ClientDto();
    Manager testManager1=new Manager();
    Manager testManager2=new Manager();

    @Test
    public void isExistsByClientId() {
        testClient1.setId(2L);
        testClient2.setId(-1L);
        when(clientRepository.existsById(testClient1.getId())).thenReturn(true);
        assertTrue(clientServiceImpl.isExistsByClientId(testClient1.getId()));
        when(clientRepository.existsById(testClient2.getId())).thenReturn(false);
        assertFalse("false", clientServiceImpl.isExistsByClientId(testClient2.getId()));
        verify(clientRepository,times(2)).existsById(any());
    }

    @Test
    public void getClientDtoListFromClientsWithManager() {
        testManager1.setId(2L);
        when(clientRepository.getClientDtoListFromClientsWithManager(testManager1.getId())).thenReturn(Collections.emptyList());
        assertEquals(Collections.emptyList(), clientServiceImpl.getClientDtoListFromClientsWithManager(testManager1));
        verify(clientRepository,times(1)).getClientDtoListFromClientsWithManager(testManager1.getId());
    }

    @Test
    public void getClientDtoByClientId() {
        testClientDto1.setId(2L);
        when(clientRepository.getClientDtoFromClientWithId(testClientDto1.getId())).thenReturn(testClientDto1);
        assertEquals(testClientDto1, clientServiceImpl.getClientDtoByClientId(testClientDto1.getId()));
        verify(clientRepository,times(1)).getClientDtoFromClientWithId(testClientDto1.getId());
    }

    @Test
    public void relationClientWithManager() {
        testClient3.setId(2L);
        testManager2.setId(2L);
        when(clientRepository.relationClientWithManager(testClient3.getId(),testManager2.getId())).thenReturn(true);
        assertTrue(clientServiceImpl.relationClientWithManager(testClient3.getId(),testManager2.getId()));
        verify(clientRepository,times(1)).relationClientWithManager(testClient3.getId(),testManager2.getId());
    }
}