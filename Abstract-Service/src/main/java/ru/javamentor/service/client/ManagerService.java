package ru.javamentor.service.client;


import ru.javamentor.dto.user.ManagerDto;

public interface ManagerService {
    void update(ManagerDto managerDto);

    boolean isExistsByManagerId(Long managerId);
}
