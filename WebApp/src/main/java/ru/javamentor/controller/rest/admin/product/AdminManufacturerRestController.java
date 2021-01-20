package ru.javamentor.controller.rest.admin.product;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javamentor.dto.product.ManufacturerPostDto;
import ru.javamentor.service.product.ManufacturerService;

@RestController
@RequestMapping("api/admin/manufactured")
public class AdminManufacturerRestController {

    private final ManufacturerService manufacturerService;

    public AdminManufacturerRestController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    /**
     * Метод для добавления поставщика в контроллере
     *
     * @param manufacturerDto - данные поставщика
     * @return - возврат http ответа
     */
    @PostMapping("/")
    public ResponseEntity<?> addManufactured(@RequestBody ManufacturerPostDto manufacturerDto) {
        manufacturerService.addManufacturer(manufacturerDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
