package ru.javamentor.service.manager;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.javamentor.dto.user.ManagerDto;
import ru.javamentor.dto.user.ManagerPostDto;
import ru.javamentor.model.user.Manager;
import ru.javamentor.repository.user.ManagerRepository;
import ru.javamentor.service.PasswordGenerator;

import javax.transaction.Transactional;

@Service
public class ManagerServiceImpl implements ManagerService {

    private final ManagerRepository managerRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final PasswordGenerator passwordGenerator;



    public ManagerServiceImpl(ManagerRepository managerRepository, BCryptPasswordEncoder passwordEncoder, PasswordGenerator passwordGenerator) {
        this.managerRepository = managerRepository;
        this.passwordEncoder = passwordEncoder;
        this.passwordGenerator = passwordGenerator;


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


    /**
     * Метод сохраняет нового менеджера
     *
     * @param managerPostDto - данные менеджера
     */
    @Transactional
    @Override
    public void saveNewManager(ManagerPostDto managerPostDto) {

        Manager managerDto = new Manager();
        managerDto.setFirstName(managerPostDto.getFirstName());
        managerDto.setLastName(managerPostDto.getLastName());
        managerDto.setPassword(passwordEncoder.encode(passwordGenerator.generateStrongPassword()));
        managerDto.setPatronymic(managerPostDto.getPatronymic());
        managerDto.setUsername(managerPostDto.getEmail());
        managerDto.setRoles(managerPostDto.getRoles());
        managerRepository.save(managerDto);

    }


    /**
     * Метод проверяет, сущестует ли менеджер с таким Email
     *
     * @param email - email предпологаемого менеджера
     * @return email менеджера
     */
    @Override
    public boolean isExistsManagerByEmail(String email) {
        return managerRepository.existsManagerByUsername(email);
    }


    /**
     * Метод возвращает имя и фамилию менеджера по email
     *
     * @param email - email менеджера
     * @return ManagerFullName менеджера
     */
    @Override
    public String getManagerFullNameByEmail(String email) {
        return managerRepository.getManagerFullNameByEmail(email);
    }
}
