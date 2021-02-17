package ru.javamentor.implementService;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javamentor.model.user.Client;
import ru.javamentor.repository.user.ClientRepository;
import ru.javamentor.service.client.ClientService;
import ru.javamentor.service.client.ClientServiceImpl;


import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests  {
    
    @Autowired
    @InjectMocks
    private ClientServiceImpl clientService;


    @MockBean
    private ClientRepository clientRepository;
    Client client=new Client();

    @Test
    public void isExistsByClientIdTest()  {

        client.setId(10L);
        Mockito.when(clientRepository.existsById(10L)).thenReturn(true);

        assertTrue(clientService.isExistsByClientId(10L));

    }
}
