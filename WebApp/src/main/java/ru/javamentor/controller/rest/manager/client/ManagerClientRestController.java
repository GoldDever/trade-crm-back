package ru.javamentor.controller.rest.manager.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javamentor.dto.order.ClientDto;

import java.util.List;

@RestController
@RequestMapping("api/manager/client")
public class ManagerClientRestController {

    @GetMapping("/all")
    ResponseEntity<?> getClientDtoListByManagerId() {
        //TODO реализовать. Метод List<ClientDto>, возвращает список клиентов прикрепленных к текущему менеджеру. Добавляем лог.
        return null;
    }

    @GetMapping("/{clientId}")
    ResponseEntity<?> getClientDtoByClientId(@PathVariable Long clientId) {
        //TODO реализовать. Метод возвращает ClientDto, клиента по id,
        // Если клиент не привязан к менеджеру, возвращаем ответ "Вы не можете просматривать данного клиента."
        // Добавить проверку на существование клиента по id.
        // Если клиента не существует, возвращаем "Клиент с id={clientId}, не найден." Добавляем лог
        return null;
    }

}
