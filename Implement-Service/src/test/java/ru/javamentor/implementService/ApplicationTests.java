package ru.javamentor.implementService;

import org.junit.jupiter.api.Test;
import ru.javamentor.service.client.ClientService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ApplicationTests  {


    private ClientService clientService;

    public ApplicationTests(ClientService clientService) {
        this.clientService = clientService;
    }

    @Test
    public void isExistsByClientIdTest() throws Exception {
        assertTrue(true, clientService.isExistsByClientId(1l));

    }
}
