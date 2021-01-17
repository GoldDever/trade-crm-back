package ru.javamentor.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.javamentor.model.order.Order;
import ru.javamentor.model.order.OrderItem;
import ru.javamentor.model.product.Product;
import ru.javamentor.model.product.ReserveProduct;
import ru.javamentor.model.user.Client;
import ru.javamentor.model.user.Manager;
import ru.javamentor.repository.order.OrderItemRepository;
import ru.javamentor.repository.order.OrderRepository;
import ru.javamentor.repository.product.ProductRepository;
import ru.javamentor.repository.product.ReserveProductRepository;
import ru.javamentor.repository.user.ClientRepository;
import ru.javamentor.repository.user.ManagerRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

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
    private BCryptPasswordEncoder passwordEncoder;

    private void init() {
        initClient();
        initManager();
        initProduct();
        initOrder();
        initOrderItem();
        initReserveProduct();
    }

    private void initClient() {
        Client client = new Client();
        client.setFirstName("ClientFirstName");
        client.setLastName("ClientLastName");
        client.setPatronymic("ClientPatronymic");
        client.setUsername("client@mail.ru");
        client.setPassword(passwordEncoder.encode("password"));
        clientRepository.save(client);
    }

    private void initManager() {
        Manager manager = new Manager();
        manager.setFirstName("ManagerFirstName");
        manager.setLastName("ManagerLastName");
        manager.setPatronymic("ManagerPatronymic");
        manager.setUsername("manager@mail.ru");
        manager.setPassword(passwordEncoder.encode("password"));
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
