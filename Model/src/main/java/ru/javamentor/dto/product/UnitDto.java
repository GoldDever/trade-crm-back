package ru.javamentor.dto.product;
/**
 * DTO для передачи на страницу единицы измерения
 */
public class UnitDto {
    private Long id;
    private String unitName;

    public UnitDto() {}

    public UnitDto(Long id, String unitName) {
        this.id = id;
        this.unitName = unitName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
}
