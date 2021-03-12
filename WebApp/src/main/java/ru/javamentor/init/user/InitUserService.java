package ru.javamentor.init.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.javamentor.model.user.Client;
import ru.javamentor.model.user.Manager;
import ru.javamentor.model.user.Role;
import ru.javamentor.model.user.User;
import ru.javamentor.repository.RoleRepository;
import ru.javamentor.repository.UserRepository;
import ru.javamentor.repository.user.ClientRepository;
import ru.javamentor.repository.user.ManagerRepository;

import java.util.Set;

@Component
public class InitUserService {
    private final ClientRepository clientRepository;
    private final ManagerRepository managerRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public InitUserService(ClientRepository clientRepository, ManagerRepository managerRepository, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.clientRepository = clientRepository;
        this.managerRepository = managerRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }


    public void initUser() {
        saveManager("alexey@mail.ru", "password", "Алексей", "Бабин", "alexey");
        saveManager("nicolla@mail.ru", "password", "Николай", "Шадрин", "nicolla");
        saveManager("moscal@mail.ru", "password", "Иван", "Москалец", "mockal");
        saveManager("efimov@mail.ru", "password", "Антон", "Ефимов", "efimov");
        saveManager("lev@mail.ru", "password", "Лев", "Николаев", "lev");
        saveManager("sergey@mail.ru", "password", "Сергей", "Сергеенко", "sergey");
        saveManager("egor@mail.ru", "password", "Егор", "Демин", "egor");

        saveClient("ООО\"Форма\"", "ivanov@mail.ru", "password", "Иван", "Иванов", "ivanov", managerRepository.findByUsername("alexey@mail.ru"));
        saveClient("ООО\"Капитал\"", "artem@mail.com", "password", "Артем", "Артемов", "artem", managerRepository.findByUsername("alexey@mail.ru"));
        saveClient("ООО\"Действие\"", "andrew@mail.com", "password", "Андрей", "Андреев", "andrew", managerRepository.findByUsername("alexey@mail.ru"));

        saveClient("ООО\"Период\"", "alena@mail.com", "password", "Алена", "Соловьева", "alena", managerRepository.findByUsername("nicolla@mail.ru"));
        saveClient("ООО\"Цель\"", "lena@mail.com", "password", "Лена", "Макашевва", "lena", managerRepository.findByUsername("nicolla@mail.ru"));
        saveClient("ООО\"Развитие\"", "evgeniya@mail.com", "password", "Евгения", "Евгеньева", "evgeniya", managerRepository.findByUsername("nicolla@mail.ru"));

        saveClient("ООО\"Интернационал\"", "yurii@mail.com", "password", "Юрий", "Борисов", "yurii", managerRepository.findByUsername("moscal@mail.ru"));
        saveClient("ООО\"Купидон\"", "boris@mail.com", "password", "Борис", "Евгеньев", "boris", managerRepository.findByUsername("moscal@mail.ru"));
        saveClient("ООО\"Берсерк\"", "pavel@mail.com", "password", "Павел", "Дунин", "pavel", managerRepository.findByUsername("moscal@mail.ru"));

        saveClient("ООО\"Платон\"", "igor@mail.com", "password", "Игорь", "Черний", "igor", managerRepository.findByUsername("efimov@mail.ru"));
        saveClient("ООО\"Юпитер\"", "alex@mail.com", "password", "Александр", "Болотный", "alex", managerRepository.findByUsername("efimov@mail.ru"));
        saveClient("ООО\"Авто Моторс\"", "matvey@mail.com", "password", "Матвей", "Хрущев", "matvey", managerRepository.findByUsername("efimov@mail.ru"));

        saveClient("ООО\"Индекс\"", "anton@mail.com", "password", "Антон", "Чехов", "anton", managerRepository.findByUsername("lev@mail.ru"));
        saveClient("ООО\"Линия Жизни\"", "dmitriy@mail.com", "password", "Дмитрий", "Еремин", "dmitriy", managerRepository.findByUsername("lev@mail.ru"));
        saveClient("ООО\"Инфраструктура\"", "leonid@mail.com", "password", "Леонид", "Макаров", "leonid", managerRepository.findByUsername("lev@mail.ru"));

        saveClient("ООО\"Завтрашний День\"", "arina@mail.com", "password", "Арина", "Печенкина", "arina", managerRepository.findByUsername("sergey@mail.ru"));
        saveClient("ООО\"Форма\"", "mikh@mail.com", "password", "Михаил", "Лазарев", "mikh", managerRepository.findByUsername("sergey@mail.ru"));
        saveClient("ООО\"Донской\"", "nikita@mail.com", "password", "Никита", "Абрамов", "nikita", managerRepository.findByUsername("sergey@mail.ru"));

        saveClient("ООО\"Афродита\"", "stepan@mail.com", "password", "Степан", "Сутулин", "stepan", managerRepository.findByUsername("egor@mail.ru"));
        saveClient("ООО\"Россия\"", "polina@mail.com", "password", "Полина", "Латкина", "polina", managerRepository.findByUsername("egor@mail.ru"));
        saveClient("ООО\"Азия\"", "nina@mail.com", "password", "Нина", "Юдина", "nina", managerRepository.findByUsername("egor@mail.ru"));

        saveAdmin("admin@mail.ru", "password", "Admin", "Admin", "Admin");
    }

    private void saveAdmin(String username, String password, String firstName, String lastName, String patronymic) {
        Set<Role> adminRoles = Set.of(roleRepository.findByRoleName("ADMIN"));
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPatronymic(patronymic);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRoles(adminRoles);
        userRepository.save(user);
    }

    private void saveManager(String username, String password, String firstName, String lastName, String patronymic) {
        Set<Role> managerRoles = Set.of(roleRepository.findByRoleName("MANAGER"));
        Manager manager = new Manager();
        manager.setFirstName(firstName);
        manager.setLastName(lastName);
        manager.setPatronymic(patronymic);
        manager.setUsername(username);
        manager.setPassword(passwordEncoder.encode(password));
        manager.setRoles(managerRoles);
        managerRepository.save(manager);
    }

    private void saveClient(String clientName, String username, String password, String firstName, String lastName, String patronymic, Manager manager) {
        Set<Role> clientRoles = Set.of(roleRepository.findByRoleName("CLIENT"));
        Client client = new Client(clientName);
        client.setFirstName(firstName);
        client.setLastName(lastName);
        client.setPatronymic(patronymic);
        client.setUsername(username);
        client.setPassword(passwordEncoder.encode(password));
        client.setRoles(clientRoles);
        client.setManager(manager);
        clientRepository.save(client);
    }
}
