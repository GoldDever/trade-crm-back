package ru.javamentor.controller.rest.manager.client;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.javamentor.dto.order.ClientDto;
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
    public ResponseEntity<?> getClientDtoListByManagerId(@AuthenticationPrincipal Manager manager) {
        List<ClientDto> clientDtoList = clientService.getClientDtoListFromClientsWithManager(manager);
        return clientDtoList != null
                ? new ResponseEntity<>(clientDtoList, HttpStatus.OK)
                : new ResponseEntity<>("У текущего менеджера нет клиентов", HttpStatus.BAD_REQUEST);

    }

    /**
     * Метод возвращает ClientDto, клиента по id
     *
     * @param clientId - id клиента
     * @return ClientDto клиента
     */
    @GetMapping("/{clientId}")
    ResponseEntity<?> getClientDtoByClientId(@PathVariable Long clientId, @AuthenticationPrincipal Manager manager) {
        //TODO реализовать. Метод возвращает ClientDto, клиента по id,
        // Если клиент не привязан к менеджеру, возвращаем ответ "Вы не можете просматривать данного клиента."
        // Добавить проверку на существование клиента по id.
        // Если клиента не существует, возвращаем "Клиент с id={clientId}, не найден." Добавляем лог

        if (!clientService.isExistsByManagerAndClientId(manager, clientId)) {
            return ResponseEntity.badRequest().body("Клиент с id=" + clientId + ", не найден.");
        } else {
            return ResponseEntity.ok().body(clientService.getClientDtoByClientId(clientId));
        }
    }
}

