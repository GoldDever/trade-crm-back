package ru.javamentor.dto.product;
/**
 * DTO для создания нового поставщика
 */
public class SupplierPostDto {
    private String name;

    public SupplierPostDto() {}

    public SupplierPostDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
