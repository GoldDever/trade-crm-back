package ru.javamentor.service.manager;


import org.springframework.stereotype.Service;
import ru.javamentor.dto.user.ManagerDto;
import ru.javamentor.model.user.Manager;
import ru.javamentor.repository.user.ManagerRepository;
import ru.javamentor.service.client.ManagerService;

import javax.transaction.Transactional;

@Service
public class ManagerServiceImpl implements ManagerService {

    private final ManagerRepository managerRepository;

    public ManagerServiceImpl(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    /**
     * Метод собирает Менеджера из DTO и обновляет его
     *
     * @param managerDto - данные менеджера
     */
    @Transactional
    @Override
    public void update(ManagerDto managerDto) {
        Manager updateManager = managerRepository.findById(managerDto.getId()).get();
        updateManager.setId(managerDto.getId());
        updateManager.setUsername(managerDto.getEmail());
        updateManager.setFirstName(managerDto.getFirstName());
        updateManager.setLastName(managerDto.getLastName());
        updateManager.setPatronymic(managerDto.getPatronymic());
        managerRepository.save(updateManager);
    }

    /**
     * Метод проверяет, сущестует ли менеджер с таким ID
     *
     * @param managerId - ID предпологаемого менеджера
     */
    @Override
    public boolean isExistsByManagerId(Long managerId) {
        return managerRepository.existsById(managerId);
    }
}
