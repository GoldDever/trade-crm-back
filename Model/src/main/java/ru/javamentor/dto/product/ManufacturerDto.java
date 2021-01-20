package ru.javamentor.dto.product;

/**
 * DTO для передачи на страницу производителя
 */
public class ManufacturerDto {

    private Long id;
    private String manufacturerName;
    private String idFromErp;

    public ManufacturerDto() {
    }

    public ManufacturerDto(Long id, String manufacturerName, String idFromErp) {
        this.id = id;
        this.manufacturerName = manufacturerName;
        this.idFromErp = idFromErp;
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

    public String getIdFromErp() {
        return idFromErp;
    }

    public void setIdFromErp(String idFromErp) {
        this.idFromErp = idFromErp;
    }
}
