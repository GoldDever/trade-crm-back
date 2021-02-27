package ru.javamentor.service.manager;

import org.springframework.stereotype.Service;
import ru.javamentor.repository.user.ManagerRepository;

import javax.transaction.Transactional;

@Service
public class ManagerServiceImpl implements ManagerService {
    private final ManagerRepository managerRepository;

    public ManagerServiceImpl(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    @Transactional
    @Override
    public boolean isExistsByManagerId(Long orderId) {
        return managerRepository.existsById(orderId);
    }
}

