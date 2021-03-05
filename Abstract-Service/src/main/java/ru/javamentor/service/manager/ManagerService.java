package ru.javamentor.service.manager;


import ru.javamentor.dto.user.ManagerDto;
import ru.javamentor.dto.user.ManagerPostDto;

public interface ManagerService {

    boolean isExistsByManagerId(Long orderId);

    void update(ManagerDto managerDto);

    void saveNewManager(ManagerPostDto managerPostDto);

    boolean isExistsManagerByEmail(String email);

    ManagerDto getManagerDtoByManagerEmail(String email);
}