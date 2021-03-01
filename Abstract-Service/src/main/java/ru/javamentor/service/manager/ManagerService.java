package ru.javamentor.service.manager;

import ru.javamentor.dto.user.ManagerDto;

public interface ManagerService {

    boolean isExistsByManagerId(Long orderId);

    void update(ManagerDto managerDto);

}