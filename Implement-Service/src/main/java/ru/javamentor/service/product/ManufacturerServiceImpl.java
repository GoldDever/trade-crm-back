package ru.javamentor.service.product;

import org.springframework.stereotype.Service;
import ru.javamentor.dto.product.ManufacturerDto;
import ru.javamentor.dto.product.ManufacturerPostDto;
import ru.javamentor.model.product.Manufacturer;
import ru.javamentor.repository.product.ManufacturerRepository;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    /**
     * Метод сохраняет нового поставщика
     *
     * @param manufacturerDto - данные поставщика
     */
    @Override
    public void addManufacturer(ManufacturerPostDto manufacturerDto) {
        Manufacturer manufacturer =
                new Manufacturer(
                        manufacturerDto.getManufacturerName(),
                        manufacturerDto.getIdFromErp());
        manufacturerRepository.save(manufacturer);
    }
}
