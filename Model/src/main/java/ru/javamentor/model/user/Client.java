package ru.javamentor.model.user;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Set;

@Entity
@DiscriminatorValue("CLIENT")
public class Client extends User {

    private String clientName;

    public Client() {
    }

    public Client(String clientName) {
        this.clientName = clientName;
    }

    public Client(String firstName, String lastName, String clientName) {
        super(firstName, lastName);
        this.clientName = clientName;
    }

    public Client(
            String username,
            String password,
            String firstName,
            String lastName,
            String patronymic,
            Set<Role> roles,
            String clientName,
            Manager manager
    ) {
        super(username, password, firstName, lastName, patronymic, roles);
        this.clientName = clientName;
        this.manager = manager;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private Manager manager;

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}
