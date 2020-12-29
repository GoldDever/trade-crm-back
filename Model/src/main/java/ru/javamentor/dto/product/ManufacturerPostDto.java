package ru.javamentor.dto.product;
/**
 * DTO для создания нового производителя
 */
public class ManufacturerPostDto {
    private String manufacturerName;

    public ManufacturerPostDto() {}

    public ManufacturerPostDto(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }
}
