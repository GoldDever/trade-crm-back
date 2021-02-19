package ru.javamentor.controller.rest.admin.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javamentor.dto.user.ClientDto;
import ru.javamentor.dto.user.ManagerDto;

@RestController
@RequestMapping("api/admin/client")
public class AdminClientRestController {

    @PostMapping()
    public ResponseEntity<?> addNewClient(ClientDto clientDto) {
        //TODO метод добавляет нового клиента.
        // Добавить проверку на существование менеджера или клиента с таким e-mail.
        // Если клиент с таким e-mail существует, то вернуть сообщение "Клиента с таким e-mail, уже существует.
        // Если менеджер с таким e-mail существует, то вернуть сообщение "На данный id, зарегистрирован менеджер Фамилия Имя."
        return ResponseEntity.ok("Клиент c email " + clientDto.getEmail() + ", успешно добавлен");
    }

    @PutMapping()
    public ResponseEntity<?> updateClient(ClientDto clientDto) {
        //TODO метод обновляет существующего клиента.
        // Добавить проверку на существование менеджера или клиента с таким id.
        // Если клиент с таким e-mail существует, то обновить.
        // Если нет, то вывести сообщение "Клиент с таким id, не существует.
        // Если менеджер с таким id существует, то вернуть сообщение "На данный id, зарегистрирован менеджер Фамилия Имя."
        return ResponseEntity.ok("Клиент c id " + clientDto.getId() + ", успешно обновлен");
    }
}
