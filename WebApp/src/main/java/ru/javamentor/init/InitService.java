package ru.javamentor.init;

import org.springframework.beans.factory.annotation.Autowired;
import ru.javamentor.model.order.Order;
import ru.javamentor.model.order.OrderItem;
import ru.javamentor.model.product.Product;
import ru.javamentor.model.user.Client;
import ru.javamentor.model.user.Manager;
import ru.javamentor.repository.order.OrderItemRepository;
import ru.javamentor.repository.order.OrderRepository;
import ru.javamentor.repository.product.ProductRepository;
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

    private void init() {
        initClient();
        initManager();
        initProduct();
        initOrder();
        initOrderItem();
    }

    private void initClient() {
        Client client = new Client();
        client.setFirstName("ClientFirstName");
        client.setLastName("ClientLastName");
        clientRepository.save(client);
    }

    private void initManager() {
        Manager manager = new Manager();
        manager.setFirstName("ManagerFirstName");
        manager.setLastName("ManagerLastName");
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
        Order order = new Order();
        order.setIdFromErp("idFromErp");
        //order.setClient(clientRepository.findById(1L).get());
        //order.setManager(managerRepository.findById(2L).get());
        order.setOrderFullPrice(BigDecimal.valueOf(70.678));
        order.setApprove(true);
        order.setPaid(true);
        order.setShipped(true);
        order.setCreateTime(LocalDateTime.parse("2020-12-30T11:03:12"));
        orderRepository.save(order);
    }

    private void initOrderItem() {
        OrderItem orderItem = new OrderItem();
        orderItem.setIdFromErp("idFromErp");
        orderItem.setInvoiceIssued("invoiceIssued");
        orderItem.setProductCount(10);
        orderItem.setProduct(productRepository.findById(1L).get());
        orderItem.setOrder(orderRepository.findById(1L).get());
        orderItem.setItemFullPrice(BigDecimal.valueOf(100.678));
        orderItemRepository.save(orderItem);
    }

}
