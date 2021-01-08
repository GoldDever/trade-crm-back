package ru.javamentor.model.user;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CLIENT")
public class Client extends User {

    private String clientName;

    public Client() {
    }

    public Client(String clientName) {
        this.clientName = clientName;
    }
}
