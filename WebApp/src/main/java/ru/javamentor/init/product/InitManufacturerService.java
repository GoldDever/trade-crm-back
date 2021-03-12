package ru.javamentor.init.product;

import org.springframework.stereotype.Component;
import ru.javamentor.model.product.Manufacturer;
import ru.javamentor.repository.product.ManufacturerRepository;

@Component
public class InitManufacturerService {

    private final ManufacturerRepository manufacturerRepository;

    public InitManufacturerService(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    public void initManufacturer() {
        createManufacturer("Карго технологии");

        createManufacturer("ООО Азимут");
    }

    private void createManufacturer(String manufacturerName) {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setManufacturerName(manufacturerName);
        manufacturerRepository.save(manufacturer);
    }
}
