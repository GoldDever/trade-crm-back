package ru.javamentor.service.order;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ru.javamentor.dto.order.OrderApproveDto;
import ru.javamentor.dto.order.OrderDto;
import ru.javamentor.dto.order.OrderItemDto;
import ru.javamentor.dto.product.ProductDto;
import ru.javamentor.dto.user.ClientDto;
import ru.javamentor.dto.user.ManagerDto;
import ru.javamentor.model.order.Order;
import ru.javamentor.model.order.OrderApprove;
import ru.javamentor.model.user.Client;
import ru.javamentor.model.user.Manager;
import ru.javamentor.repository.order.OrderApproveRepository;
import ru.javamentor.repository.order.OrderItemRepository;
import ru.javamentor.repository.order.OrderRepository;
import ru.javamentor.repository.product.ReserveProductRepository;
import ru.javamentor.repository.user.ClientRepository;
import ru.javamentor.repository.user.ManagerRepository;
import ru.javamentor.service.product.ProductServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {

    @Mock
    OrderRepository orderRepository;
    @Mock
    OrderApproveRepository orderApproveRepository;
    @Mock
    ReserveProductRepository reserveProductRepository;
    @Mock
    ClientRepository clientRepository;
    @Mock
    ManagerRepository managerRepository;
    @Mock
    OrderItemRepository orderItemRepository;
    @Mock
    ProductServiceImpl productService;

    @InjectMocks
    OrderServiceImpl orderService;

    @Test
    public void updateApproveStatus() {
        final boolean approve = true;

        Order order = new Order();
        order.setId(1L);
        OrderApproveDto orderApproveDto = new OrderApproveDto();
        orderApproveDto.setApprove(approve);

        when(orderRepository.findById(order.getId())).thenReturn(Optional.of(order));

        doAnswer(invocationOnMock -> null)
                .when(orderRepository).save(order);

        AtomicReference<OrderApprove> orderApproveAR = new AtomicReference<>();
        doAnswer(invocationOnMock -> {
            orderApproveAR.set(invocationOnMock.getArgument(0));
            return null;
        }).when(orderApproveRepository).save(any(OrderApprove.class));

        orderService.updateApproveStatus(orderApproveDto, order.getId());

        assertEquals(order.getApprove(), approve);
        assertEquals(orderApproveAR.get().isApprove(), approve);
        assertEquals(orderApproveAR.get().getOrder(), order);
    }

    @Test
    public void updateShippedStatus() {

        String orderIdFromErp = "A005";
        Long orderId = 10L;

        when(orderRepository.getOrderIdByIdFromErp(orderIdFromErp)).thenReturn(orderId);
        doNothing().when(reserveProductRepository).deleteByOrderId(orderId);

        assertEquals(orderService.updateShippedStatus(orderIdFromErp), orderId);
    }

    @Test
    public void newOrder() {

        Client client = new Client();
        client.setId(1L);
        Manager manager = new Manager();
        manager.setId(5L);

        when(clientRepository.findById(client.getId())).thenReturn(Optional.of(client));
        when(managerRepository.findById(manager.getId())).thenReturn(Optional.of(manager));

        AtomicReference<Order> orderAR = new AtomicReference<>();
        doAnswer(invocationOnMock -> {
            orderAR.set(invocationOnMock.getArgument(0));
            return null;
        }).when(orderRepository).save(any(Order.class));

        orderService.newOrder(client.getId(), manager);
        assertEquals(orderAR.get().getClient(), client);
        assertEquals(orderAR.get().getManager(), manager);
    }

    @Test
    public void getOrderDtoByOrderId() {

        Order order = new Order();
        order.setId(1L);

        OrderDto orderDto = new OrderDto();
        ClientDto clientDto = new ClientDto();
        ManagerDto managerDto = new ManagerDto();
        List<OrderItemDto> orderItemDtoList = new ArrayList<>();
        ProductDto productDto = new ProductDto();

        when(orderRepository.getOrderDtoWithOrderId(order.getId())).thenReturn(orderDto);
        when(orderRepository.getClientIdByOrderId(order.getId())).thenReturn(clientDto.getId());
        when(orderRepository.getManagerIdByOrderId(order.getId())).thenReturn(managerDto.getId());
        when(clientRepository.getClientDtoFromClientWithId(clientDto.getId())).thenReturn(clientDto);
        when(managerRepository.getManagerDtoById(managerDto.getId())).thenReturn(managerDto);
        when(orderItemRepository.getListOrderItemDtoByOrderId(orderDto.getId())).thenReturn(orderItemDtoList);
        when(orderItemRepository.findProductIdByOrderItemId(orderDto.getId())).thenReturn(any(Long.class));

        when(productService.getProductDtoByProductId(any(Long.class))).thenReturn(any(ProductDto.class));

        assertEquals(orderService.getOrderDtoByOrderId(order.getId()), orderDto);
    }
}
