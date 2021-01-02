package ru.javamentor.dto.product;

/**
 * DTO для передачи на страницу производителя
 */
public class ManufacturerDto {
    private Long id;
    private String manufacturerName;

    public ManufacturerDto() {
    }

    public ManufacturerDto(Long id, String manufacturerName) {
        this.id = id;
        this.manufacturerName = manufacturerName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }
}
