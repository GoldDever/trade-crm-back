package ru.javamentor.controller.rest.manager.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.javamentor.model.user.Manager;
import ru.javamentor.service.client.ClientService;
import ru.javamentor.model.user.Client;


import java.util.List;

@RestController
@RequestMapping("api/manager/client")
public class ManagerClientRestController {

    public final ClientService clientService;

    public ManagerClientRestController(ClientService clientService) {
        this.clientService = clientService;
    }


    /**
     * Метод  возвращает список клиентов прикрепленных к текущему менеджеру
     *
     * @param manager - Менеджер
     * @return список клиентов
     */
    @GetMapping("/all")
    ResponseEntity<?> getClientDtoListByManagerId() {
        //TODO реализовать. Метод List<ClientDto>, возвращает список клиентов прикрепленных к текущему менеджеру. Добавляем лог.
        return null;
    }

    @GetMapping("/{clientId}")
    ResponseEntity<?> getClientDtoByClientId(@PathVariable Long clientId, @AuthenticationPrincipal Manager manager) {
        //TODO реализовать. Метод возвращает ClientDto, клиента по id,
        // Если клиент не привязан к менеджеру, возвращаем ответ "Вы не можете просматривать данного клиента."
        // Добавить проверку на существование клиента по id.
        // Если клиента не существует, возвращаем "Клиент с id={clientId}, не найден." Добавляем лог
        return null;
    }

        if(!clientService.isExistsByClientId(clientId)) {
            return ResponseEntity.badRequest().body("Клиент с id=" + clientId + ", не найден.");
        }
        Client client = clientService.getClientById(clientId);
        if (!client.getManager().equals(manager)) {
            return ResponseEntity.badRequest().body("Вы не можете просматривать данного клиента.");
        } else {
            return ResponseEntity.ok().body(clientService.getClientDtoByClientId(clientId));
        }
    }
}
