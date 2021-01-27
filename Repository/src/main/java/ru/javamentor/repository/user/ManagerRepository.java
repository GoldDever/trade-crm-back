package ru.javamentor.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.javamentor.dto.order.ManagerDto;
import ru.javamentor.model.user.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Long> {

    @Query("select new ru.javamentor.dto.order.ManagerDto(m.id, m.firstName," +
            "m.lastName) from Manager m where m.id =:managerId")
    ManagerDto getManagerDtoById(@Param("managerId") Long managerId);
}
