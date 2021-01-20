package ru.javamentor.model.user;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("CLIENT")
public class Client extends User {

    private String clientName;

    public Client() {
    }

    public Client(String clientName) {
        this.clientName = clientName;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    private Manager manager;

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}
