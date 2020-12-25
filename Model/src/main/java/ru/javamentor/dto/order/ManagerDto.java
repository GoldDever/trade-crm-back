package ru.javamentor.dto.order;

/**
 *  * DTO для передачи Manager на страницу
 */
public class ManagerDto {
    private Long id;
    private String firstName;
    private String secondName;

    public ManagerDto() {
    }

    public ManagerDto(Long id, String firstName, String secondName) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }
}
