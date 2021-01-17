package ru.javamentor.service.product;

import ru.javamentor.dto.product.ManufacturerDto;
import ru.javamentor.dto.product.ManufacturerPostDto;

public interface ManufacturerService {
    void addManufacturer(ManufacturerPostDto manufacturerDto);
}
