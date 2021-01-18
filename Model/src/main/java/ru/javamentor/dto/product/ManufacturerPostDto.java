package ru.javamentor.dto.product;
/**
 * DTO для создания нового производителя
 */
public class ManufacturerPostDto {

    private String manufacturerName;
    private String idFromErp;

    public ManufacturerPostDto(String manufacturerName, String idFromErp) {
        this.manufacturerName = manufacturerName;
        this.idFromErp = idFromErp;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public String getIdFromErp() {
        return idFromErp;
    }

    public void setIdFromErp(String idFromErp) {
        this.idFromErp = idFromErp;
    }
}
