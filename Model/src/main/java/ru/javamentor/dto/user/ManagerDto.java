package ru.javamentor.dto.user;

/**
 *  * DTO для передачи Manager на страницу
 */
public class ManagerDto extends UserDto {

    public ManagerDto() {
    }

    public ManagerDto(Long id, String firstName, String lastName, String patronymic, String username) {
        super(id, firstName, lastName, patronymic, username);
    }
}
