package ru.javamentor.controller.rest.admin.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javamentor.dto.user.ClientDto;
import ru.javamentor.dto.user.ManagerDto;
import ru.javamentor.model.user.Client;
import ru.javamentor.model.user.Manager;
import ru.javamentor.service.client.ClientService;

@RestController
@RequestMapping("api/admin/client")
public class AdminClientRestController {

    ClientService clientService;

    public AdminClientRestController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping()
    public ResponseEntity<?> addNewClient(ClientDto clientDto) {
        //TODO метод добавляет нового клиента.
        // Добавить проверку на существование менеджера или клиента с таким e-mail.
        // Если клиент с таким e-mail существует, то вернуть сообщение "Клиента с таким e-mail, уже существует.
        // Если менеджер с таким e-mail существует, то вернуть сообщение "На данный id, зарегистрирован менеджер Фамилия Имя."
        return ResponseEntity.ok("Клиент c email " + clientDto.getEmail() + ", успешно добавлен");
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateClient(@RequestBody ClientDto clientDto) {
clientService.updateClient(clientDto);
        return ResponseEntity.ok("Клиент c id " + clientDto.getId() + ", успешно обновлен");
            //TODO метод обновляет существующего клиента.
            // Добавить проверку на существование менеджера или клиента с таким id.
            // Если клиент с таким e-mail существует, то обновить.
            // Если нет, то вывести сообщение "Клиент с таким id, не существует.
            // Если менеджер с таким id существует, то вернуть сообщение "На данный id, зарегистрирован менеджер Фамилия Имя."

        }
    }
