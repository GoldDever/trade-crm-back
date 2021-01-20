package ru.javamentor.model.user;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@DiscriminatorValue("MANAGER")
public class Manager extends User {

    public Manager() {
    }

    public Manager(String firstName, String lastName) {
        super(firstName, lastName);
    }

    @OneToMany
    private Set<Client> client;


    public Set<Client> getClient() {
        return client;
    }

    public void setClients(Set<Client> client) {
        this.client = client;
    }
}
