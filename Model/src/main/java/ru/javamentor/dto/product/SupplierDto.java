package ru.javamentor.dto.product;
/**
 * DTO для передачи на страницу поставщика
 */
public class SupplierDto {

    private Long id;
    private String name;
    public String idFromErp;

    public SupplierDto() {}

    public SupplierDto(Long id, String name, String idFromErp) {
        this.id = id;
        this.name = name;
        this.idFromErp=idFromErp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdFromErp(){ return idFromErp;}

    public void setIdFromErp(String idFromErp) { this.idFromErp=idFromErp;}
    }

