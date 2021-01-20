package ru.javamentor.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
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
        this.reserveProductRepository = reserveProductRepository;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
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
        Client client = new Client();
        client.setFirstName("ClientFirstName");
        client.setLastName("ClientLastName");
        client.setPatronymic("ClientPatronymic");
        client.setUsername("client@mail.ru");
        client.setPassword(passwordEncoder.encode("password"));
        Set<Role> roles = Set.of(roleRepository.findByRoleName("CLIENT"));
        client.setRoles(roles);
        clientRepository.save(client);
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
        Client client1 = clientRepository.findById(1L).get();
        Manager manager1 = managerRepository.findById(2L).get();
        Order order = new Order();
        order.setIdFromErp("idFromErp");
        order.setClient(client1);
        order.setManager(manager1);
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
