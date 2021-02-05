package ru.javamentor.dto.order;

/**
 *  * DTO для передачи Client на страницу
 */
public class ClientDto {
    private Long id;
    private String name;
    private String clientName;

    public ClientDto() {
    }

    public ClientDto(Long id, String name, String clientName) {
        this.id = id;
        this.name = name;
        this.clientName = clientName;
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

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
}

