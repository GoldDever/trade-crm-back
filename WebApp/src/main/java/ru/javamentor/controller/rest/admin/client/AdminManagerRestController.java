package ru.javamentor.controller.rest.admin.client;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javamentor.dto.user.ClientDto;
import ru.javamentor.dto.user.ManagerDto;
import ru.javamentor.dto.user.ManagerPostDto;
import ru.javamentor.service.client.ClientService;
import ru.javamentor.service.manager.ManagerService;


@RestController
@RequestMapping("api/admin/manager")
public class AdminManagerRestController {

    private final ManagerService managerService;
    private final ClientService clientService;


    public AdminManagerRestController(ManagerService managerService, ClientService clientService) {
        this.managerService = managerService;
        this.clientService = clientService;
    }

    @PostMapping("/")
    public ResponseEntity<?> addNewManager(@RequestBody ManagerPostDto managerDto) {

        if (clientService.isExistsClientByEmail(managerDto.getEmail())) {
            return new ResponseEntity<>("На данный email, зарегистрирован клиент " +
                    clientService.getClientFullNameByEmail(managerDto.getEmail()), HttpStatus.BAD_REQUEST);
        } else if (managerService.isExistsManagerByEmail(managerDto.getEmail())) {
            return new ResponseEntity<>("Менеджер с таким email уже существует", HttpStatus.BAD_REQUEST);

        } else {
            managerService.saveNewManager(managerDto);
            return ResponseEntity.ok("Менеджер c email " + managerDto.getEmail() + ", успешно добавлен");
        }

    }

    @PutMapping()
    public ResponseEntity<?> updateManager(@RequestBody ManagerDto managerDto) {

        if (clientService.isExistsByClientId(managerDto.getId()) && !managerService.isExistsByManagerId(managerDto.getId())) {
            ClientDto clientDto = clientService.getClientDtoByClientId(managerDto.getId());
            return new ResponseEntity<>("На данный id, зарегистрирован клиент " + clientDto.getClientName(), HttpStatus.BAD_REQUEST);
        } else if (managerService.isExistsByManagerId(managerDto.getId())) {
            managerService.update(managerDto);
            return ResponseEntity.ok("Менеджер c id " + managerDto.getId() + ", успешно обновлен");
        } else {
            return new ResponseEntity<>("Менеджера с таким id, не существует", HttpStatus.BAD_REQUEST);
        }
    }

}
