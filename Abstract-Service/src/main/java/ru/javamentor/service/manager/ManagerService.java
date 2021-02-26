package ru.javamentor.service.manager;

import ru.javamentor.dto.user.ManagerPostDto;
import ru.javamentor.model.user.Manager;

public interface ManagerService {

    boolean isExistsByManagerId(Long orderId);

    boolean isExistsByManagerEmail(String email);

    Manager saveManager(ManagerPostDto managerDto);
}