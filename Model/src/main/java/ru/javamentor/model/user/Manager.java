package ru.javamentor.model.user;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("MANAGER")
public class Manager extends User {
    public Manager(String firstName, String secondName) {
    }

    public Manager() {

    }
}
