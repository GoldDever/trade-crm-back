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
        Manufacturer manufacturer1 = new Manufacturer();
        manufacturer1.setManufacturerName("Карго технологии");
        manufacturer1.setIdFromErp("123123123123");
        manufacturerRepository.save(manufacturer1);

        Manufacturer manufacturer2 = new Manufacturer();
        manufacturer2.setManufacturerName("ООО Азимут");
        manufacturer2.setIdFromErp("456456456456");
        manufacturerRepository.save(manufacturer2);
    }
}
