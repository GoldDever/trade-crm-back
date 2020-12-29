package ru.javamentor.dto.product;
/**
 * DTO для создания новой единицы измерения
 */
public class UnitPostDto {
    private String unitName;

    public UnitPostDto() {}

    public UnitPostDto(String unitName) {
        this.unitName = unitName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
}
