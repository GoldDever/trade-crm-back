package ru.javamentor.service.client;

public interface ClientService {
    boolean isExistsByClientId(Long clientId);
    boolean clientBelongsToManager(Long clientId, Long managerId);
}
