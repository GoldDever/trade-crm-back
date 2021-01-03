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
     * Method set DTO to Entity
     * for manufacturer
     *
     * @param manufacturerDto manufacturer data
     */
    @Override
    public void addManufacturer(ManufacturerDto manufacturerDto) {
        Manufacturer manufacturer = new Manufacturer();

        manufacturer.setManufacturerName(manufacturerDto.getManufacturerName());
        manufacturer.setIdFromErp(manufacturerDto.getIdFromErp());

        manufacturerRepository.save(manufacturer);
    }
}
