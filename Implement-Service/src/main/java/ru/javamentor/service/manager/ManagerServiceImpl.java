package ru.javamentor.service.manager;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.javamentor.dto.user.ManagerPostDto;
import ru.javamentor.model.user.Manager;
import ru.javamentor.repository.user.ManagerRepository;

import javax.transaction.Transactional;

@Service
public class ManagerServiceImpl implements ManagerService {
    private final ManagerRepository managerRepository;
    private final PasswordEncoder passwordEncoder;

    public ManagerServiceImpl(ManagerRepository managerRepository, PasswordEncoder passwordEncoder) {
        this.managerRepository = managerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public boolean isExistsByManagerId(Long orderId) {
        return managerRepository.existsById(orderId);
    }


    /**
     * Метод возвращает boolean при проверке существования клиента с данным Id.
     *
     * @param email - почта клиента
     * @return - Возвращает boolean, соответствующий результату.
     */
    @Transactional
    @Override
    public boolean isExistsByManagerEmail(String email) {
        return managerRepository.existsByUsername(email);
    }

    /**
     * Метод сохраняет нового менеджера
     *
     * @param managerDto - данные менеджера
     */
    @Transactional
    @Override
    public Manager saveManager(ManagerPostDto managerDto) {

        Manager manager = new Manager();
        manager.setFirstName(managerDto.getFirstName());
        manager.setLastName(managerDto.getLastName());
        manager.setPassword(passwordEncoder.encode(managerDto.getPassword()));
        manager.setPatronymic(managerDto.getPatronymic());
        manager.setUsername(managerDto.getUsername());
        manager.setClientName(managerDto.getClientName());
        manager.setRoles(managerDto.getRoles());
        return managerRepository.save(manager);
    }
}

