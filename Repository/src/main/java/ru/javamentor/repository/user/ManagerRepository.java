package ru.javamentor.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.javamentor.dto.user.ManagerDto;
import ru.javamentor.model.user.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Long> {

    @Query("select new ru.javamentor.dto.user.ManagerDto(m.id, m.firstName, m.lastName, m.patronymic, m.username) " +
            "from Manager m where m.id =:managerId")
    ManagerDto getManagerDtoById(@Param("managerId") Long managerId);

    @Query("select concat(m.lastName,' ',m.firstName) from Manager m where m.username =:managerEmail")
    String getManagerFullNamePerEmail(@Param("managerEmail") String email);

    boolean existsManagerByUsername(String username);

    Manager findByUsername(String username);
}
