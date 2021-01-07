package ru.javamentor.service.product;

import org.springframework.stereotype.Service;
import ru.javamentor.dto.product.ManufacturerDto;
import ru.javamentor.model.product.Manufacturer;
import ru.javamentor.repository.product.ManufacturerRepository;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    /**
     * Метод установливает данные из DTO
     * в сущность производителя и сохраняет объект в БД
     *
     * @param manufacturerDto - данные поставщика
     */
    @Override
    public void addManufacturer(ManufacturerDto manufacturerDto) {
        Manufacturer manufacturer = new Manufacturer();

        manufacturer.setManufacturerName(manufacturerDto.getManufacturerName());
        manufacturer.setIdFromErp(manufacturerDto.getIdFromErp());

        manufacturerRepository.save(manufacturer);
    }
}
