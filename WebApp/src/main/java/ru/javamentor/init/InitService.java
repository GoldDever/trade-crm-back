package ru.javamentor.init;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.javamentor.model.order.Order;
import ru.javamentor.model.order.OrderItem;
import ru.javamentor.model.product.Manufacturer;
import ru.javamentor.model.product.Product;
import ru.javamentor.model.product.ProductCategory;
import ru.javamentor.model.product.ReserveProduct;
import ru.javamentor.model.product.Supplier;
import ru.javamentor.model.product.Unit;
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
import ru.javamentor.repository.product.ManufacturerRepository;
import ru.javamentor.repository.product.SupplierRepository;
import ru.javamentor.repository.product.ProductCategoryRepository;
import ru.javamentor.repository.product.UnitRepository;
import ru.javamentor.repository.user.ClientRepository;
import ru.javamentor.repository.user.ManagerRepository;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Component
public class InitService {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final ManagerRepository managerRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final SupplierRepository supplierRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final UnitRepository unitRepository;
    private final ReserveProductRepository reserveProductRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public InitService(
            OrderRepository orderRepository,
            ClientRepository clientRepository,
            ManagerRepository managerRepository,
            OrderItemRepository orderItemRepository,
            ProductRepository productRepository,
            ManufacturerRepository manufacturerRepository,
            SupplierRepository supplierRepository,
            ProductCategoryRepository productCategoryRepository,
            UnitRepository unitRepository,
            ReserveProductRepository reserveProductRepository,
            RoleRepository roleRepository,
            UserRepository userRepository,
            BCryptPasswordEncoder passwordEncoder
    ) {
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
        this.managerRepository = managerRepository;
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.supplierRepository = supplierRepository;
        this.productCategoryRepository = productCategoryRepository;
        this.unitRepository = unitRepository;
        this.reserveProductRepository = reserveProductRepository;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    private void init() {
        initRole();
        initManager();
        initClient();
        initAdmin();
        initManufacturer();
        initProductCategory();
        initSupplier();
        initUnit();
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
        client1.setFirstName("Иван");
        client1.setLastName("Иванов");
        client1.setPatronymic("ivanov");
        client1.setUsername("ivanov@mail.ru");
        client1.setPassword(passwordEncoder.encode("password"));
        Set<Role> roles1 = Set.of(roleRepository.findByRoleName("CLIENT"));
        client1.setRoles(roles1);
        Manager manager = managerRepository.findById(1L).get();
        client1.setManager(manager);
        clientRepository.save(client1);

        Client client2 = new Client("ООО\"Капитал\"");
        client2.setFirstName("Артем");
        client2.setLastName("Артемов");
        client2.setPatronymic("artem");
        client2.setUsername("artem@mail.com");
        client2.setPassword(passwordEncoder.encode("password"));
        Set<Role> roles2 = Set.of(roleRepository.findByRoleName("CLIENT"));
        client2.setRoles(roles2);
        Manager manager2 = managerRepository.findById(1L).get();
        client2.setManager(manager2);
        clientRepository.save(client2);

        Client client3 = new Client("ООО\"Действие\"");
        client3.setFirstName("Андрей");
        client3.setLastName("Андреев");
        client3.setPatronymic("andrew");
        client3.setUsername("andrew@mail.com");
        client3.setPassword(passwordEncoder.encode("password"));
        Set<Role> roles3 = Set.of(roleRepository.findByRoleName("CLIENT"));
        client3.setRoles(roles3);
        Manager manager3 = managerRepository.findById(1L).get();
        client3.setManager(manager3);
        clientRepository.save(client3);

        Client client4 = new Client("ООО\"Период\"");
        client4.setFirstName("Алена");
        client4.setLastName("Соловьева");
        client4.setPatronymic("alena");
        client4.setUsername("alena@mail.com");
        client4.setPassword(passwordEncoder.encode("password"));
        Set<Role> roles4 = Set.of(roleRepository.findByRoleName("CLIENT"));
        client4.setRoles(roles4);
        Manager manager4 = managerRepository.findById(2L).get();
        client4.setManager(manager4);
        clientRepository.save(client4);

        Client client5 = new Client("ООО\"Цель\"");
        client5.setFirstName("Лена");
        client5.setLastName("Макашевва");
        client5.setPatronymic("lena");
        client5.setUsername("lena@mail.com");
        client5.setPassword(passwordEncoder.encode("password"));
        Set<Role> roles5 = Set.of(roleRepository.findByRoleName("CLIENT"));
        client5.setRoles(roles5);
        Manager manager5 = managerRepository.findById(2L).get();
        client5.setManager(manager5);
        clientRepository.save(client5);

        Client client6 = new Client("ООО\"Развитие\"");
        client6.setFirstName("Евгения");
        client6.setLastName("Евгеньева");
        client6.setPatronymic("evgeniya");
        client6.setUsername("evgeniya@mail.com");
        client6.setPassword(passwordEncoder.encode("password"));
        Set<Role> roles6 = Set.of(roleRepository.findByRoleName("CLIENT"));
        client6.setRoles(roles6);
        Manager manager6 = managerRepository.findById(2L).get();
        client6.setManager(manager6);
        clientRepository.save(client6);

        Client client7 = new Client("ООО\"Интернационал\"");
        client7.setFirstName("Юрий");
        client7.setLastName("Борисов");
        client7.setPatronymic("yurii");
        client7.setUsername("yurii@mail.com");
        client7.setPassword(passwordEncoder.encode("password"));
        Set<Role> roles7 = Set.of(roleRepository.findByRoleName("CLIENT"));
        client7.setRoles(roles7);
        Manager manager7 = managerRepository.findById(3L).get();
        client7.setManager(manager7);
        clientRepository.save(client7);

        Client client8 = new Client("ООО\"Купидон\"");
        client8.setFirstName("Борис");
        client8.setLastName("Евгеньев");
        client8.setPatronymic("boris");
        client8.setUsername("boris@mail.com");
        client8.setPassword(passwordEncoder.encode("password"));
        Set<Role> roles8 = Set.of(roleRepository.findByRoleName("CLIENT"));
        client8.setRoles(roles8);
        Manager manager8 = managerRepository.findById(3L).get();
        client8.setManager(manager8);
        clientRepository.save(client8);

        Client client9 = new Client("ООО\"Берсерк\"");
        client9.setFirstName("Павел");
        client9.setLastName("Дунин");
        client9.setPatronymic("pavel");
        client9.setUsername("pavel@mail.com");
        client9.setPassword(passwordEncoder.encode("password"));
        Set<Role> roles9 = Set.of(roleRepository.findByRoleName("CLIENT"));
        client9.setRoles(roles9);
        Manager manager9 = managerRepository.findById(3L).get();
        client9.setManager(manager9);
        clientRepository.save(client9);

        Client client10 = new Client("ООО\"Платон\"");
        client10.setFirstName("Игорь");
        client10.setLastName("Черний");
        client10.setPatronymic("igor");
        client10.setUsername("igor@mail.com");
        client10.setPassword(passwordEncoder.encode("password"));
        Set<Role> roles10 = Set.of(roleRepository.findByRoleName("CLIENT"));
        client10.setRoles(roles10);
        Manager manager10 = managerRepository.findById(4L).get();
        client10.setManager(manager10);
        clientRepository.save(client10);

        Client client11 = new Client("ООО\"Юпитер\"");
        client11.setFirstName("Александр");
        client11.setLastName("Болотный");
        client11.setPatronymic("alex");
        client11.setUsername("alex@mail.com");
        client11.setPassword(passwordEncoder.encode("password"));
        Set<Role> roles11 = Set.of(roleRepository.findByRoleName("CLIENT"));
        client11.setRoles(roles11);
        Manager manager11 = managerRepository.findById(4L).get();
        client11.setManager(manager11);
        clientRepository.save(client11);

        Client client12 = new Client("ООО\"Авто Моторс\"");
        client12.setFirstName("Матвей");
        client12.setLastName("Хрущев");
        client12.setPatronymic("matvey");
        client12.setUsername("matvey@mail.com");
        client12.setPassword(passwordEncoder.encode("password"));
        Set<Role> roles12 = Set.of(roleRepository.findByRoleName("CLIENT"));
        client12.setRoles(roles12);
        Manager manager12 = managerRepository.findById(4L).get();
        client12.setManager(manager12);
        clientRepository.save(client12);

        Client client13 = new Client("ООО\"Индекс\"");
        client13.setFirstName("Антон");
        client13.setLastName("Чехов");
        client13.setPatronymic("anton");
        client13.setUsername("anton@mail.com");
        client13.setPassword(passwordEncoder.encode("password"));
        Set<Role> roles13 = Set.of(roleRepository.findByRoleName("CLIENT"));
        client13.setRoles(roles13);
        Manager manager13 = managerRepository.findById(5L).get();
        client13.setManager(manager13);
        clientRepository.save(client13);

        Client client14 = new Client("ООО\"Линия Жизни\"");
        client14.setFirstName("Дмитрий");
        client14.setLastName("Еремин");
        client14.setPatronymic("dmitriy");
        client14.setUsername("dmitriy@mail.com");
        client14.setPassword(passwordEncoder.encode("password"));
        Set<Role> roles14 = Set.of(roleRepository.findByRoleName("CLIENT"));
        client14.setRoles(roles14);
        Manager manager14 = managerRepository.findById(5L).get();
        client14.setManager(manager14);
        clientRepository.save(client14);

        Client client15 = new Client("ООО\"Инфраструктура\"");
        client15.setFirstName("Леонид");
        client15.setLastName("Макаров");
        client15.setPatronymic("leonid");
        client15.setUsername("leonid@mail.com");
        client15.setPassword(passwordEncoder.encode("password"));
        Set<Role> roles15 = Set.of(roleRepository.findByRoleName("CLIENT"));
        client15.setRoles(roles15);
        Manager manager15 = managerRepository.findById(5L).get();
        client15.setManager(manager15);
        clientRepository.save(client15);

        Client client16 = new Client("ООО\"Завтрашний День\"");
        client16.setFirstName("Арина");
        client16.setLastName("Печенкина");
        client16.setPatronymic("arina");
        client16.setUsername("arina@mail.com");
        client16.setPassword(passwordEncoder.encode("password"));
        Set<Role> roles16 = Set.of(roleRepository.findByRoleName("CLIENT"));
        client16.setRoles(roles16);
        Manager manager16 = managerRepository.findById(6L).get();
        client16.setManager(manager16);
        clientRepository.save(client16);

        Client client17 = new Client("ООО\"Форма\"");
        client17.setFirstName("Михаил");
        client17.setLastName("Лазарев");
        client17.setPatronymic("mikh");
        client17.setUsername("mikh@mail.com");
        client17.setPassword(passwordEncoder.encode("password"));
        Set<Role> roles17 = Set.of(roleRepository.findByRoleName("CLIENT"));
        client17.setRoles(roles17);
        Manager manager17 = managerRepository.findById(6L).get();
        client17.setManager(manager17);
        clientRepository.save(client17);

        Client client18 = new Client("ООО\"Донской\"");
        client18.setFirstName("Никита");
        client18.setLastName("Абрамов");
        client18.setPatronymic("nikita");
        client18.setUsername("nikita@mail.com");
        client18.setPassword(passwordEncoder.encode("password"));
        Set<Role> roles18 = Set.of(roleRepository.findByRoleName("CLIENT"));
        client18.setRoles(roles18);
        Manager manager18 = managerRepository.findById(6L).get();
        client18.setManager(manager18);
        clientRepository.save(client18);

        Client client19 = new Client("ООО\"Афродита\"");
        client19.setFirstName("Стапан");
        client19.setLastName("Сутулин");
        client19.setPatronymic("stepan");
        client19.setUsername("stepan@mail.com");
        client19.setPassword(passwordEncoder.encode("password"));
        Set<Role> roles19 = Set.of(roleRepository.findByRoleName("CLIENT"));
        client19.setRoles(roles19);
        Manager manager19 = managerRepository.findById(7L).get();
        client19.setManager(manager19);
        clientRepository.save(client19);

        Client client20 = new Client("ООО\"Россия\"");
        client20.setFirstName("Полина");
        client20.setLastName("Латкина");
        client20.setPatronymic("polina");
        client20.setUsername("polina@mail.com");
        client20.setPassword(passwordEncoder.encode("password"));
        Set<Role> roles20 = Set.of(roleRepository.findByRoleName("CLIENT"));
        client20.setRoles(roles20);
        Manager manager20 = managerRepository.findById(7L).get();
        client20.setManager(manager20);
        clientRepository.save(client20);

        Client client21 = new Client("ООО\"Азия\"");
        client21.setFirstName("Нина");
        client21.setLastName("Юдина");
        client21.setPatronymic("nina");
        client21.setUsername("nina@mail.com");
        client21.setPassword(passwordEncoder.encode("password"));
        Set<Role> roles21 = Set.of(roleRepository.findByRoleName("CLIENT"));
        client21.setRoles(roles21);
        Manager manager21 = managerRepository.findById(7L).get();
        client21.setManager(manager21);
        clientRepository.save(client21);
    }

    private void initManager() {
        Manager manager1 = new Manager();
        manager1.setFirstName("Алексей");
        manager1.setLastName("Бабин");
        manager1.setPatronymic("alexey");
        manager1.setUsername("alexey@mail.ru");
        manager1.setPassword(passwordEncoder.encode("password"));
        Set<Role> roles1 = Set.of(roleRepository.findByRoleName("MANAGER"));
        manager1.setRoles(roles1);
        managerRepository.save(manager1);

        Manager manager2 = new Manager();
        manager2.setFirstName("Николай");
        manager2.setLastName("Шадрин");
        manager2.setPatronymic("nicolla");
        manager2.setUsername("nicolla@mail.ru");
        manager2.setPassword(passwordEncoder.encode("password"));
        Set<Role> roles2 = Set.of(roleRepository.findByRoleName("MANAGER"));
        manager2.setRoles(roles2);
        managerRepository.save(manager2);

        Manager manager3 = new Manager();
        manager3.setFirstName("Иван");
        manager3.setLastName("Москалец");
        manager3.setPatronymic("mockal");
        manager3.setUsername("moscal@mail.ru");
        manager3.setPassword(passwordEncoder.encode("password"));
        Set<Role> roles3 = Set.of(roleRepository.findByRoleName("MANAGER"));
        manager3.setRoles(roles3);
        managerRepository.save(manager3);

        Manager manager4 = new Manager();
        manager4.setFirstName("Антон");
        manager4.setLastName("Ефимов");
        manager4.setPatronymic("efimov");
        manager4.setUsername("efimov@mail.ru");
        manager4.setPassword(passwordEncoder.encode("password"));
        Set<Role> roles4 = Set.of(roleRepository.findByRoleName("MANAGER"));
        manager4.setRoles(roles4);
        managerRepository.save(manager4);

        Manager manager5 = new Manager();
        manager5.setFirstName("Лев");
        manager5.setLastName("Николаев");
        manager5.setPatronymic("lev");
        manager5.setUsername("lev@mail.ru");
        manager5.setPassword(passwordEncoder.encode("password"));
        Set<Role> roles5 = Set.of(roleRepository.findByRoleName("MANAGER"));
        manager5.setRoles(roles5);
        managerRepository.save(manager5);

        Manager manager6 = new Manager();
        manager6.setFirstName("Сергей");
        manager6.setLastName("Сергеенко");
        manager6.setPatronymic("sergey");
        manager6.setUsername("sergey@mail.ru");
        manager6.setPassword(passwordEncoder.encode("password"));
        Set<Role> roles6 = Set.of(roleRepository.findByRoleName("MANAGER"));
        manager6.setRoles(roles6);
        managerRepository.save(manager6);

        Manager manager7 = new Manager();
        manager7.setFirstName("Егор");
        manager7.setLastName("Демин");
        manager7.setPatronymic("egor");
        manager7.setUsername("egor@mail.ru");
        manager7.setPassword(passwordEncoder.encode("password"));
        Set<Role> roles7 = Set.of(roleRepository.findByRoleName("MANAGER"));
        manager7.setRoles(roles7);
        managerRepository.save(manager7);
    }

    private void initProduct() {
        Product product = new Product();
        product.setProductCount(333);
        product.setProductName("productName");
        product.setMadeCountry("madeCountry");
        product.setManufacturer(manufacturerRepository.findManufacturerById(1L));
        Set<Supplier> suppliers = Set.copyOf(supplierRepository.findAll());
        product.setSuppliers(suppliers);
        product.setUnit(unitRepository.findUnitById(1L));
        product.setProductCategory(productCategoryRepository.findById(2L).get());
        product.setArticle("article");
        product.setPurchasePrice(BigDecimal.valueOf(6.00));
        product.setPrice(BigDecimal.valueOf(4.00));
        product.setMargin(BigDecimal.valueOf(2.00));
        product.setPackagingCount(3);
        product.setIdFromErp("123123123123");
        productRepository.save(product);
    }

    private void initProduct2() {
        Product product = new Product();
        product.setProductCount(100);
        product.setProductName("productName1");
        product.setMadeCountry("madeCountry1");
        product.setManufacturer(manufacturerRepository.findManufacturerById(2L));
        Set<Supplier> suppliers = Set.copyOf(supplierRepository.findAll());
        product.setSuppliers(suppliers);
        product.setUnit(unitRepository.findUnitById(2L));
        product.setProductCategory(productCategoryRepository.findById(1L).get());
        product.setArticle("article1");
        product.setPurchasePrice(BigDecimal.valueOf(7.00));
        product.setPrice(BigDecimal.valueOf(5.00));
        product.setMargin(BigDecimal.valueOf(3.00));
        product.setPackagingCount(4);
        product.setIdFromErp("456456456456");
        productRepository.save(product);
    }

    private void initManufacturer() {
        Manufacturer manufacturer1 =  new Manufacturer();
        manufacturer1.setManufacturerName("Карго технологии");
        manufacturer1.setIdFromErp("123123123123");
        manufacturerRepository.save(manufacturer1);

        Manufacturer manufacturer2 =  new Manufacturer();
        manufacturer2.setManufacturerName("ООО Азимут");
        manufacturer2.setIdFromErp("456456456456");
        manufacturerRepository.save(manufacturer2);
    }

    private void initSupplier() {
        Supplier supplier1 = new Supplier();
        supplier1.setName("Полигон");
        supplierRepository.save(supplier1);

        Supplier supplier2 = new Supplier();
        supplier2.setName("Партком");
        supplierRepository.save(supplier2);

        Supplier supplier3 = new Supplier();
        supplier3.setName("Бригадир");
        supplierRepository.save(supplier3);

    }

    private void initProductCategory() {
        ProductCategory productCategory1 = new ProductCategory();
        productCategory1.setCategoryName("Главные");
        productCategoryRepository.save(productCategory1);
        
        ProductCategory productCategory2 = new ProductCategory();
        productCategory2.setCategoryName("Товары");
        productCategory2.setMainProductCategory(productCategory1);
        productCategoryRepository.save(productCategory2);

    }

    private void initUnit() {
        Unit unit1 = new Unit();
        unit1.setUnitName("кг");
        unitRepository.save(unit1);

        Unit unit2 = new Unit();
        unit2.setUnitName("шт");
        unitRepository.save(unit2);

    }

    private void initOrder() {
        Client client1 = clientRepository.findById(21L).get();
        Manager manager1 = managerRepository.findById(1L).get();
        Order order = new Order();
        order.setIdFromErp("idFromErp");
        order.setClient(client1);
        order.setManager(manager1);
        order.setApprove(true);
        order.setPaid(true);
        order.setShipped(true);
        order.setCreateTime(LocalDateTime.parse("2020-12-30T11:03:12"));
        orderRepository.save(order);

        Client client2 = clientRepository.findById(20L).get();
        Order order2 = new Order();
        order.setIdFromErp("idFromErp");
        order.setClient(client2);
        order.setManager(manager1);
        order.setApprove(true);
        order.setPaid(true);
        order.setShipped(true);
        order.setCreateTime(LocalDateTime.parse("2020-11-30T11:03:12"));
        orderRepository.save(order2);
    }

    private void initOrderItem() {
        OrderItem orderItem1 = new OrderItem();
        orderItem1.setInvoiceIssued("invoiceIssued");
        orderItem1.setProductCount(10);
        orderItem1.setProduct(productRepository.findById(1L).get());
        orderItem1.setOrder(orderRepository.findById(1L).get());
        orderItemRepository.save(orderItem1);

        OrderItem orderItem2 = new OrderItem();
        orderItem2.setInvoiceIssued("invoiceIssued");
        orderItem2.setProductCount(2);
        orderItem2.setProduct(productRepository.findById(1L).get());
        orderItem2.setOrder(orderRepository.findById(1L).get());
        orderItemRepository.save(orderItem2);
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
