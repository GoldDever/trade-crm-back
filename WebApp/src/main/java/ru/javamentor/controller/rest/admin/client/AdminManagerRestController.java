package ru.javamentor.controller.rest.admin.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javamentor.dto.user.ManagerDto;

@RestController
@RequestMapping("api/admin/manager")
public class AdminManagerRestController {

    @PostMapping()
    public ResponseEntity<?> addNewManager(ManagerDto managerDto) {
        //TODO метод добавляет нового менеджера.
        // Добавить проверку на существование менеджера или клиента с таким e-mail.
        // Если менеджер с таким e-mail существует, то вернуть сообщение "Менеджер с таким e-mail, уже существует.
        // Если клиент с таким e-mail существует, то вернуть сообщение "На данный e-mail, зарегистрирован клиент ООО "Название клиента".
        return ResponseEntity.ok("Менеджер c email " + managerDto.getEmail() + ", успешно добавлен");
    }

    @PutMapping()
    public ResponseEntity<?> updateManager(ManagerDto managerDto) {
        //TODO метод обновляет существующего менеджера.
        // Добавить проверку на существование менеджера или клиента с таким id.
        // Если менеджер с таким e-mail существует, то обновить.
        // Если нет, то вывести сообщение "Менеджер с таким id, не существует.
        // Если клиент с таким id существует, то вернуть сообщение "На данный id, зарегистрирован клиент ООО "Название клиента".
        return ResponseEntity.ok("Менеджер c id " + managerDto.getId() + ", успешно обновлен");
    }

}
