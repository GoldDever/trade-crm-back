package ru.javamentor.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.javamentor.model.order.Order;
import ru.javamentor.model.order.OrderItem;
import ru.javamentor.model.product.Product;
import ru.javamentor.model.product.ReserveProduct;
import ru.javamentor.model.user.Client;
import ru.javamentor.model.user.Manager;
import ru.javamentor.model.user.Role;
import ru.javamentor.model.user.User;
import ru.javamentor.repository.RoleRepository;
import ru.javamentor.repository.UserRepository;
import ru.javamentor.repository.order.OrderItemRepository;
import ru.javamentor.repository.order.OrderRepository;
import ru.javamentor.repository.product.ProductRepository;
import ru.javamentor.repository.product.ReserveProductRepository;
import ru.javamentor.repository.user.ClientRepository;
import ru.javamentor.repository.user.ManagerRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public class InitService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ReserveProductRepository reserveProductRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private void init() {
        initRole();
        initClient();
        initManager();
        initAdmin();
        initProduct();
        initOrder();
        initOrderItem();
        initReserveProduct();
        initProduct2();
    }

    private void initRole() {
        roleRepository.save(new Role("ADMIN"));
        roleRepository.save(new Role("MANAGER"));
        roleRepository.save(new Role("CLIENT"));
    }

    private void initAdmin() {
        User admin = new User();
        admin.setFirstName("Admin");
        admin.setLastName("Admin");
        admin.setPatronymic("Admin");
        admin.setUsername("admin@mail.ru");
        admin.setPassword(passwordEncoder.encode("password"));
        Set<Role> roles = Set.of(roleRepository.findByRoleName("ADMIN"));
        admin.setRoles(roles);
        userRepository.save(admin);
    }

    private void initClient() {

        Client client1 = new Client("ООО\"Форма\"");
        client1.setId(1L);
        client1.setFirstName("Иван");
        client1.setLastName("Иванов");
        client1.setPatronymic("ivanov");
        client1.setUsername("ivanov@mail.ru");
        client1.setPassword(passwordEncoder.encode("ivanov"));
        Set<Role> roles1 = Set.of(roleRepository.findByRoleName("CLIENT"));
        client1.setRoles(roles1);
        clientRepository.save(client1);

        Client client2 = new Client("ООО\"Капитал\"");
        client2.setId(2L);
        client2.setFirstName("Артем");
        client2.setLastName("Артемов");
        client2.setPatronymic("artem");
        client2.setUsername("artem@mail.com");
        client2.setPassword(passwordEncoder.encode("artem"));
        Set<Role> roles2 = Set.of(roleRepository.findByRoleName("CLIENT"));
        client2.setRoles(roles2);
        clientRepository.save(client2);

        Client client3 = new Client("ООО\"Действие\"");
        client3.setId(3L);
        client3.setFirstName("Андрей");
        client3.setLastName("Андреев");
        client3.setPatronymic("andrew");
        client3.setUsername("andrew@mail.com");
        client3.setPassword(passwordEncoder.encode("andrew"));
        Set<Role> roles3 = Set.of(roleRepository.findByRoleName("CLIENT"));
        client3.setRoles(roles3);
        clientRepository.save(client3);

        Client client4 = new Client("ООО\"Период\"");
        client4.setId(4L);
        client4.setFirstName("Алена");
        client4.setLastName("Соловьева");
        client4.setPatronymic("alena");
        client4.setUsername("alena@mail.com");
        client4.setPassword(passwordEncoder.encode("password5"));
        Set<Role> roles4 = Set.of(roleRepository.findByRoleName("CLIENT"));
        client4.setRoles(roles4);
        clientRepository.save(client4);

        Client client5 = new Client("ООО\"Цель\"");
        client5.setId(5L);
        client5.setFirstName("Лена");
        client5.setLastName("Макашевва");
        client5.setPatronymic("lena");
        client5.setUsername("lena@mail.com");
        client5.setPassword(passwordEncoder.encode("password6"));
        Set<Role> roles5 = Set.of(roleRepository.findByRoleName("CLIENT"));
        client5.setRoles(roles5);
        clientRepository.save(client5);

        Client client6 = new Client("ООО\"Развитие\"");
        client6.setId(6L);
        client6.setFirstName("Евгения");
        client6.setLastName("Евгеньева");
        client6.setPatronymic("evgeniya");
        client6.setUsername("evgeniya@mail.com");
        client6.setPassword(passwordEncoder.encode("password7"));
        Set<Role> roles6 = Set.of(roleRepository.findByRoleName("CLIENT"));
        client6.setRoles(roles6);
        clientRepository.save(client6);

        Client client7 = new Client("ООО\"Интернационал\"");
        client7.setId(7L);
        client7.setFirstName("Юрий");
        client7.setLastName("Борисов");
        client7.setPatronymic("yurii");
        client7.setUsername("yurii@mail.com");
        client7.setPassword(passwordEncoder.encode("password8"));
        Set<Role> roles7 = Set.of(roleRepository.findByRoleName("CLIENT"));
        client7.setRoles(roles7);
        clientRepository.save(client7);

        Client client8 = new Client("ООО\"Купидон\"");
        client8.setId(8L);
        client8.setFirstName("Борис");
        client8.setLastName("Евгеньев");
        client8.setPatronymic("boris");
        client8.setUsername("boris@mail.com");
        client8.setPassword(passwordEncoder.encode("password9"));
        Set<Role> roles8 = Set.of(roleRepository.findByRoleName("CLIENT"));
        client8.setRoles(roles8);
        clientRepository.save(client8);

        Client client9 = new Client("ООО\"Берсерк\"");
        client9.setId(9L);
        client9.setFirstName("Павел");
        client9.setLastName("Дунин");
        client9.setPatronymic("pavel");
        client9.setUsername("pavel@mail.com");
        client9.setPassword(passwordEncoder.encode("password10"));
        Set<Role> roles9 = Set.of(roleRepository.findByRoleName("CLIENT"));
        client9.setRoles(roles9);
        clientRepository.save(client9);

        Client client10 = new Client("ООО\"Платон\"");
        client10.setId(10L);
        client10.setFirstName("Игорь");
        client10.setLastName("Черний");
        client10.setPatronymic("igor");
        client10.setUsername("igor@mail.com");
        client10.setPassword(passwordEncoder.encode("password11"));
        Set<Role> roles10 = Set.of(roleRepository.findByRoleName("CLIENT"));
        client10.setRoles(roles10);
        clientRepository.save(client10);

        Client client11 = new Client("ООО\"Юпитер\"");
        client11.setId(11L);
        client11.setFirstName("Александр");
        client11.setLastName("Болотный");
        client11.setPatronymic("alex");
        client11.setUsername("alex@mail.com");
        client11.setPassword(passwordEncoder.encode("password12"));
        Set<Role> roles11 = Set.of(roleRepository.findByRoleName("CLIENT"));
        client11.setRoles(roles11);
        clientRepository.save(client11);

        Client client12 = new Client("ООО\"Авто Моторс\"");
        client12.setId(12L);
        client12.setFirstName("Матвей");
        client12.setLastName("Хрущев");
        client12.setPatronymic("matvey");
        client12.setUsername("matvey@mail.com");
        client12.setPassword(passwordEncoder.encode("password13"));
        Set<Role> roles12 = Set.of(roleRepository.findByRoleName("CLIENT"));
        client12.setRoles(roles12);
        clientRepository.save(client12);

        Client client13 = new Client("ООО\"Индекс\"");
        client13.setId(13L);
        client13.setFirstName("Антон");
        client13.setLastName("Чехов");
        client13.setPatronymic("anton");
        client13.setUsername("anton@mail.com");
        client13.setPassword(passwordEncoder.encode("password14"));
        Set<Role> roles13 = Set.of(roleRepository.findByRoleName("CLIENT"));
        client13.setRoles(roles13);
        clientRepository.save(client13);

        Client client14 = new Client("ООО\"Линия Жизни\"");
        client14.setId(14L);
        client14.setFirstName("Дмитрий");
        client14.setLastName("Еремин");
        client14.setPatronymic("dmitriy");
        client14.setUsername("dmitriy@mail.com");
        client14.setPassword(passwordEncoder.encode("password15"));
        Set<Role> roles14 = Set.of(roleRepository.findByRoleName("CLIENT"));
        client14.setRoles(roles14);
        clientRepository.save(client14);

        Client client15 = new Client("ООО\"Инфраструктура\"");
        client15.setId(16L);
        client15.setFirstName("Леонид");
        client15.setLastName("Макаров");
        client15.setPatronymic("leonid");
        client15.setUsername("leonid@mail.com");
        client15.setPassword(passwordEncoder.encode("password16"));
        Set<Role> roles15 = Set.of(roleRepository.findByRoleName("CLIENT"));
        client15.setRoles(roles15);
        clientRepository.save(client15);

        Client client16 = new Client("ООО\"Завтрашний День\"");
        client16.setId(16L);
        client16.setFirstName("Арина");
        client16.setLastName("Печенкина");
        client16.setPatronymic("arina");
        client16.setUsername("arina@mail.com");
        client16.setPassword(passwordEncoder.encode("password17"));
        Set<Role> roles16 = Set.of(roleRepository.findByRoleName("CLIENT"));
        client16.setRoles(roles16);
        clientRepository.save(client16);

        Client client17 = new Client("ООО\"Форма\"");
        client17.setId(17L);
        client17.setFirstName("Михаил");
        client17.setLastName("Лазарев");
        client17.setPatronymic("mikh");
        client17.setUsername("mikh@mail.com");
        client17.setPassword(passwordEncoder.encode("password18"));
        Set<Role> roles17 = Set.of(roleRepository.findByRoleName("CLIENT"));
        client17.setRoles(roles17);
        clientRepository.save(client17);

        Client client18 = new Client("ООО\"Донской\"");
        client18.setId(18L);
        client18.setFirstName("Никита");
        client18.setLastName("Абрамов");
        client18.setPatronymic("nikita");
        client18.setUsername("nikita@mail.com");
        client18.setPassword(passwordEncoder.encode("password19"));
        Set<Role> roles18 = Set.of(roleRepository.findByRoleName("CLIENT"));
        client18.setRoles(roles18);
        clientRepository.save(client18);

        Client client19 = new Client("ООО\"Афродита\"");
        client19.setId(19L);
        client19.setFirstName("Стапан");
        client19.setLastName("Сутулин");
        client19.setPatronymic("stepan");
        client19.setUsername("stepan@mail.com");
        client19.setPassword(passwordEncoder.encode("password20"));
        Set<Role> roles19 = Set.of(roleRepository.findByRoleName("CLIENT"));
        client19.setRoles(roles19);
        clientRepository.save(client19);

        Client client20 = new Client("ООО\"Россия\"");
        client20.setId(20L);
        client20.setFirstName("Полина");
        client20.setLastName("Латкина");
        client20.setPatronymic("polina");
        client20.setUsername("polina@mail.com");
        client20.setPassword(passwordEncoder.encode("password21"));
        Set<Role> roles20 = Set.of(roleRepository.findByRoleName("CLIENT"));
        client20.setRoles(roles20);
        clientRepository.save(client20);

        Client client21 = new Client("ООО\"Азия\"");
        client21.setId(21L);
        client21.setFirstName("Нина");
        client21.setLastName("Юдина");
        client21.setPatronymic("nina");
        client21.setUsername("nina@mail.com");
        client21.setPassword(passwordEncoder.encode("password22"));
        Set<Role> roles21 = Set.of(roleRepository.findByRoleName("CLIENT"));
        client21.setRoles(roles21);
        clientRepository.save(client21);
    }

    private void initManager() {
        Manager manager = new Manager();
        manager.setFirstName("ManagerFirstName");
        manager.setLastName("ManagerLastName");
        manager.setPatronymic("ManagerPatronymic");
        manager.setUsername("manager@mail.ru");
        manager.setPassword(passwordEncoder.encode("password"));
        Set<Role> roles = Set.of(roleRepository.findByRoleName("MANAGER"));
        manager.setRoles(roles);
        managerRepository.save(manager);

        Manager manager1 = new Manager();
        manager1.setId(1L);
        manager1.setFirstName("Алексей");
        manager1.setLastName("Бабин");
        manager1.setPatronymic("alexey");
        manager1.setUsername("alexey@mail.ru");
        manager1.setPassword(passwordEncoder.encode("password24"));
        Set<Role> roles1 = Set.of(roleRepository.findByRoleName("MANAGER"));
        manager1.setRoles(roles1);
        Set<Client> clients1 = Set.of(clientRepository.findById(1L).get()
                ,clientRepository.findById(2L).get()
                ,clientRepository.findById(3L).get());
        manager1.setClients(clients1);
        managerRepository.save(manager1);

        Manager manager2 = new Manager();
        manager2.setId(8L);
        manager2.setFirstName("Николай");
        manager2.setLastName("Шадрин");
        manager2.setPatronymic("nicolla");
        manager2.setUsername("nicolla@mail.ru");
        manager2.setPassword(passwordEncoder.encode("password25"));
        Set<Role> roles2 = Set.of(roleRepository.findByRoleName("MANAGER"));
        manager2.setRoles(roles2);
        Set<Client> clients2 = Set.of(clientRepository.findById(4L).get()
                ,clientRepository.findById(5L).get()
                ,clientRepository.findById(6L).get());
        manager2.setClients(clients2);
        managerRepository.save(manager2);

        Manager manager3 = new Manager();
        manager3.setId(3L);
        manager3.setFirstName("Иван");
        manager3.setLastName("Москалец");
        manager3.setPatronymic("mockal");
        manager3.setUsername("moscal@mail.ru");
        manager3.setPassword(passwordEncoder.encode("password26"));
        Set<Role> roles3 = Set.of(roleRepository.findByRoleName("MANAGER"));
        manager3.setRoles(roles3);
        Set<Client> clients3 = Set.of(clientRepository.findById(7L).get(),
                clientRepository.findById(8L).get(),
                clientRepository.findById(9L).get());
        manager3.setClients(clients3);
        managerRepository.save(manager3);

        Manager manager4 = new Manager();
        manager4.setId(4L);
        manager4.setFirstName("Антон");
        manager4.setLastName("Ефимов");
        manager4.setPatronymic("efimov");
        manager4.setUsername("efimov@mail.ru");
        manager4.setPassword(passwordEncoder.encode("password27"));
        Set<Role> roles4 = Set.of(roleRepository.findByRoleName("MANAGER"));
        manager4.setRoles(roles4);
        Set<Client> clients4 = Set.of(clientRepository.findById(10L).get(),
                clientRepository.findById(11L).get(),
                clientRepository.findById(12L).get());
        manager4.setClients(clients4);
        managerRepository.save(manager4);

        Manager manager5 = new Manager();
        manager5.setId(5L);
        manager5.setFirstName("Лев");
        manager5.setLastName("Николаев");
        manager5.setPatronymic("lev");
        manager5.setUsername("lev@mail.ru");
        manager5.setPassword(passwordEncoder.encode("password28"));
        Set<Role> roles5 = Set.of(roleRepository.findByRoleName("MANAGER"));
        manager5.setRoles(roles5);
        Set<Client> clients5 = Set.of(clientRepository.findById(13L).get(),
                clientRepository.findById(14L).get(),
                clientRepository.findById(15L).get());
        manager5.setClients(clients5);
        managerRepository.save(manager5);

        Manager manager6 = new Manager();
        manager6.setId(6L);
        manager6.setFirstName("Сергей");
        manager6.setLastName("Сергеенко");
        manager6.setPatronymic("sergey");
        manager6.setUsername("sergey@mail.ru");
        manager6.setPassword(passwordEncoder.encode("password29"));
        Set<Role> roles6 = Set.of(roleRepository.findByRoleName("MANAGER"));
        manager6.setRoles(roles6);
        Set<Client> clients6 = Set.of(clientRepository.findById(16L).get(),
                clientRepository.findById(17L).get(),
                clientRepository.findById(18L).get());
        manager6.setClients(clients6);
        managerRepository.save(manager6);

        Manager manager7 = new Manager();
        manager7.setId(7L);
        manager7.setFirstName("Егор");
        manager7.setLastName("Демин");
        manager7.setPatronymic("egor");
        manager7.setUsername("egor@mail.ru");
        manager7.setPassword(passwordEncoder.encode("password30"));
        Set<Role> roles7 = Set.of(roleRepository.findByRoleName("MANAGER"));
        manager7.setRoles(roles7);
        Set<Client> clients7 = Set.of(clientRepository.findById(19L).get(),
                clientRepository.findById(20L).get(),
                clientRepository.findById(21L).get());
        manager7.setClients(clients7);
        managerRepository.save(manager7);


    }

    private void initProduct() {
        Product product = new Product();
        product.setProductCount(333);
        product.setProductName("productName");
        product.setMadeCountry("madeCountry");
        product.setArticle("article");
        product.setPurchasePrice(BigDecimal.valueOf(6.00));
        product.setPrice(BigDecimal.valueOf(4.00));
        product.setMargin(BigDecimal.valueOf(2.00));
        product.setPackagingCount(3);
        productRepository.save(product);
    }

    private void initProduct2() {
        Product product = new Product();
        product.setProductCount(100);
        product.setProductName("productName1");
        product.setMadeCountry("madeCountry1");
        product.setArticle("article1");
        product.setPurchasePrice(BigDecimal.valueOf(7.00));
        product.setPrice(BigDecimal.valueOf(5.00));
        product.setMargin(BigDecimal.valueOf(3.00));
        product.setPackagingCount(4);
        productRepository.save(product);
    }

    private void initOrder() {
        Order order = new Order();
        order.setIdFromErp("idFromErp");
        order.setOrderFullPrice(BigDecimal.valueOf(70.678));
        order.setApprove(true);
        order.setPaid(true);
        order.setShipped(true);
        order.setCreateTime(LocalDateTime.parse("2020-12-30T11:03:12"));
        orderRepository.save(order);
    }

    private void initOrderItem() {
        OrderItem orderItem = new OrderItem();
        orderItem.setInvoiceIssued("invoiceIssued");
        orderItem.setProductCount(10);
        orderItem.setProduct(productRepository.findById(1L).get());
        orderItem.setOrder(orderRepository.findById(1L).get());
        orderItem.setItemFullPrice(BigDecimal.valueOf(100.678));
        orderItemRepository.save(orderItem);
    }

    private void initReserveProduct() {
        ReserveProduct reserveProduct = new ReserveProduct();
        reserveProduct.setId(1L);
        reserveProduct.setProductCount(23);
        reserveProduct.setProduct(productRepository.findById(1L).get());
        reserveProduct.setOrder(orderRepository.findById(1L).get());
        reserveProductRepository.save(reserveProduct);

        ReserveProduct reserveProduct2 = new ReserveProduct();
        reserveProduct2.setId(1L);
        reserveProduct2.setProductCount(15);
        reserveProduct2.setProduct(productRepository.findById(1L).get());
        reserveProduct2.setOrder(orderRepository.findById(1L).get());
        reserveProductRepository.save(reserveProduct2);
    }
}
