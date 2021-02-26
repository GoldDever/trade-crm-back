package ru.javamentor.dto.user;


/**
 * * DTO для передачи Client на страницу
 */

public class ClientDto extends UserDto {

    private String clientName;

    public ClientDto() {
    }

    public ClientDto(Long id, String firstName, String lastName, String patronymic, String email, String clientName) {
        super(id, firstName, lastName, patronymic, email);
        this.clientName = clientName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
}

