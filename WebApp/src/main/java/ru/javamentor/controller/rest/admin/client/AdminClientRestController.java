package ru.javamentor.controller.rest.admin.client;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javamentor.dto.user.ClientDto;
import ru.javamentor.dto.user.ClientPostDto;
import ru.javamentor.model.user.Manager;
import ru.javamentor.repository.user.ManagerRepository;
import ru.javamentor.service.client.ClientService;
import ru.javamentor.service.manager.ManagerService;

@RestController
@RequestMapping("api/admin/client")
public class AdminClientRestController {

    ClientService clientService;
    ManagerService managerService;
    ManagerRepository managerRepository;

    public AdminClientRestController(ClientService clientService, ManagerService managerService, ManagerRepository managerRepository) {
        this.clientService = clientService;
        this.managerService = managerService;
        this.managerRepository = managerRepository;
    }


    @PostMapping("/save")
    public ResponseEntity<?> addNewClient(@RequestBody ClientPostDto clientDto) throws Exception {
        //TODO метод добавляет нового клиента.
        // Добавить проверку на существование менеджера или клиента с таким e-mail.
        // Если клиент с таким e-mail существует, то вернуть сообщение "Клиента с таким e-mail, уже существует.
        // Если менеджер с таким e-mail существует, то вернуть сообщение "На данный id, зарегистрирован менеджер Фамилия Имя."
        if (clientService.isExistsByClientEmail(clientDto.getUsername())) {
            return new ResponseEntity<>("Клиент с таким e-mail уже существует", HttpStatus.BAD_REQUEST);
        }
        Manager manager = managerRepository.findByUsername(clientDto.getUsername());
        if (managerRepository.existsByUsername(clientDto.getUsername()))
            return new ResponseEntity<>("На данный id зарегистрирован менеджер " + manager.getLastName() + " " +
                    manager.getFirstName(), HttpStatus.BAD_REQUEST);
        clientService.saveClient(clientDto);
        return ResponseEntity.ok("Клиент c email " + clientDto.getUsername() + ", успешно добавлен");

    }


    @PutMapping("/update")
    public ResponseEntity<?> updateClient(@RequestBody ClientDto clientDto) {

        if (!clientService.isExistsByClientId(clientDto.getId()) && managerService.isExistsByManagerId(clientDto.getId())) {
            Manager manager = managerRepository.findById(clientDto.getId()).get();
            return new ResponseEntity<>("На данный id, зарегистрирован менеджер " + manager.getLastName() + " " +
                    manager.getFirstName(), HttpStatus.BAD_REQUEST);
        } else if (clientService.isExistsByClientId(clientDto.getId())) {
            clientService.updateClient(clientDto);
            return ResponseEntity.ok("Клиент c id " + clientDto.getId() + ", успешно обновлен");
        } else {
            return new ResponseEntity<>("Клиент с таким id, не существует", HttpStatus.BAD_REQUEST);
        }
    }
}
