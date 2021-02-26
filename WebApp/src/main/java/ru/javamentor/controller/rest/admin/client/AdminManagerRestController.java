package ru.javamentor.controller.rest.admin.client;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javamentor.dto.user.ManagerDto;
import ru.javamentor.dto.user.ManagerPostDto;
import ru.javamentor.model.user.Client;
import ru.javamentor.repository.user.ClientRepository;
import ru.javamentor.service.manager.ManagerService;

@RestController
@RequestMapping("api/admin/manager")
public class AdminManagerRestController {

    private final ClientRepository clientRepository;
    private final ManagerService managerService;

    public AdminManagerRestController(ClientRepository clientRepository, ManagerService managerService) {
        this.clientRepository = clientRepository;
        this.managerService = managerService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> addNewManager(@RequestBody ManagerPostDto managerDto) {
        //TODO метод добавляет нового менеджера.
        // Добавить проверку на существование менеджера или клиента с таким e-mail.
        // Если менеджер с таким e-mail существует, то вернуть сообщение "Менеджер с таким e-mail, уже существует.
        // Если клиент с таким e-mail существует, то вернуть сообщение "На данный e-mail, зарегистрирован клиент ООО "Название клиента".
        if (managerService.isExistsByManagerEmail(managerDto.getUsername())) {
            return new ResponseEntity<>("Менеджер с таким e-mail уже существует", HttpStatus.BAD_REQUEST);
        }
        Client client = clientRepository.findByUsername(managerDto.getUsername());
        if (clientRepository.existsByUsername(managerDto.getUsername()))
            return new ResponseEntity<>("На данный id зарегистрирован клиент " + client.getLastName() + " " +
                    client.getFirstName(), HttpStatus.BAD_REQUEST);
        managerService.saveManager(managerDto);
        return ResponseEntity.ok("Менеджер c email " + managerDto.getUsername() + ", успешно добавлен");

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
