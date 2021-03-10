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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
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

        verify(orderRepository, times(1)).findById(order.getId());
        verify(orderRepository, times(1)).save(order);
        verify(orderApproveRepository, times(1)).save(any());

    }

    @Test
    public void updateShippedStatus() {

        String orderIdFromErp = "A005";
        Long orderId = 10L;

        when(orderRepository.getOrderIdByIdFromErp(orderIdFromErp)).thenReturn(orderId);
        doNothing().when(orderRepository).updateOrderShippedStatus(orderId);
        doNothing().when(reserveProductRepository).deleteByOrderId(orderId);

        assertEquals(orderService.updateShippedStatus(orderIdFromErp), orderId);

        verify(orderRepository, times(1)).getOrderIdByIdFromErp(orderIdFromErp);
        verify(orderRepository, times(1)).updateOrderShippedStatus(orderId);
        verify(reserveProductRepository, times(1)).deleteByOrderId(orderId);

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

        verify(clientRepository, times(1)).findById(client.getId());
        verify(managerRepository, times(1)).findById(manager.getId());
        verify(orderRepository, times(1)).save(any());
    }

    @Test
    public void getOrderDtoByOrderId() {

        Order order = new Order();
        order.setId(1L);

        OrderDto orderDto = new OrderDto();
        orderDto.setId(1L);
        orderDto.setIdFromErp("22");
        orderDto.setApproved(true);

        ClientDto clientDto = new ClientDto();
        clientDto.setId(2L);

        ManagerDto managerDto = new ManagerDto();
        managerDto.setId(3L);

        List<OrderItemDto> orderItemDtoList = new ArrayList<>();
        orderItemDtoList.add(new OrderItemDto(11L, "test1", 2, 1, BigDecimal.ONE));
        orderItemDtoList.add(new OrderItemDto(12L, "test2", 9, 2, BigDecimal.ONE));
        orderItemDtoList.add(new OrderItemDto(13L, "test3", 5, 3, BigDecimal.ONE));

        when(orderRepository.getOrderDtoWithOrderId(order.getId())).thenReturn(orderDto);
        when(orderRepository.getClientIdByOrderId(order.getId())).thenReturn(clientDto.getId());
        when(orderRepository.getManagerIdByOrderId(order.getId())).thenReturn(managerDto.getId());
        when(clientRepository.getClientDtoFromClientWithId(clientDto.getId())).thenReturn(clientDto);
        when(managerRepository.getManagerDtoById(managerDto.getId())).thenReturn(managerDto);
        when(orderItemRepository.getListOrderItemDtoByOrderId(orderDto.getId())).thenReturn(orderItemDtoList);

        when(orderItemRepository.findProductIdByOrderItemId(21L)).thenReturn(any(Long.class));
        when(orderItemRepository.findProductIdByOrderItemId(22L)).thenReturn(any(Long.class));
        when(orderItemRepository.findProductIdByOrderItemId(23L)).thenReturn(any(Long.class));

        when(productService.getProductDtoByProductId(21L)).thenReturn(any(ProductDto.class));
        when(productService.getProductDtoByProductId(22L)).thenReturn(any(ProductDto.class));
        when(productService.getProductDtoByProductId(23L)).thenReturn(any(ProductDto.class));

        assertEquals(orderService.getOrderDtoByOrderId(order.getId()), orderDto);

        verify(orderRepository, times(1)).getOrderDtoWithOrderId(order.getId());
        verify(orderRepository, times(1)).getClientIdByOrderId(order.getId());
        verify(orderRepository, times(1)).getManagerIdByOrderId(order.getId());

        verify(clientRepository, times(1)).getClientDtoFromClientWithId(clientDto.getId());
        verify(managerRepository, times(1)).getManagerDtoById(managerDto.getId());
        verify(orderItemRepository, times(1)).getListOrderItemDtoByOrderId(order.getId());

        verify(orderItemRepository, times(3)).findProductIdByOrderItemId(any());
        verify(productService, times(3)).getProductDtoByProductId(any());

    }

    @Test
    public void isExistsByOrderId() {
        Long orderId = 5L;
        Long noOrderId = -1L;

        when(orderRepository.existsById(5L)).thenReturn(true);
        when(orderRepository.existsById(-1L)).thenReturn(false);

        assertTrue(orderRepository.existsById(orderId));
        assertFalse(orderRepository.existsById(noOrderId));

        verify(orderRepository, times(2)).existsById(any());
    }

    @Test
    public void getOrderDtoListByClientId() {

        Client client = new Client();
        client.setId(1L);

        OrderDto orderDto1 = new OrderDto();
        orderDto1.setId(11L);
        OrderDto orderDto2 = new OrderDto();
        orderDto2.setId(12L);
        OrderDto orderDto3 = new OrderDto();
        orderDto3.setId(13L);

        List<OrderDto> orderDtoList = new ArrayList<>();

        orderDtoList.add(orderDto1);
        orderDtoList.add(orderDto2);
        orderDtoList.add(orderDto3);

        when(orderRepository.getOrderDtoListWithClientId(client.getId())).thenReturn(orderDtoList);
        when(clientRepository.getClientDtoFromClientWithId(client.getId())).thenReturn(any());
        orderDtoList.forEach(orderDto -> {
            when(orderItemRepository.getListOrderItemDtoByOrderId(orderDto.getId())).thenReturn(new ArrayList<>());
            ManagerDto managerDto = new ManagerDto();
            managerDto.setId(orderDto.getId() + 10);
            when(orderRepository.getManagerIdByOrderId(orderDto.getId())).thenReturn(managerDto.getId());
            when(managerRepository.getManagerDtoById(managerDto.getId())).thenReturn(managerDto);
        });

        assertEquals(orderService.getOrderDtoListByClientId(client.getId()), orderDtoList);

        verify(orderRepository, times(1)).getOrderDtoListWithClientId(client.getId());
        verify(clientRepository, times(1)).getClientDtoFromClientWithId(client.getId());
        verify(orderItemRepository, times(3)).getListOrderItemDtoByOrderId(any());
        verify(orderRepository, times(3)).getManagerIdByOrderId(any());
        verify(managerRepository, times(3)).getManagerDtoById(any());
    }

    @Test
    public void getAllOrderDtoListByManagerId() {

        Manager manager = new Manager();
        manager.setId(1L);

        OrderDto orderDto1 = new OrderDto();
        orderDto1.setId(11L);
        OrderDto orderDto2 = new OrderDto();
        orderDto2.setId(12L);
        OrderDto orderDto3 = new OrderDto();
        orderDto3.setId(13L);

        List<OrderDto> orderDtoList = new ArrayList<>();

        orderDtoList.add(orderDto1);
        orderDtoList.add(orderDto2);
        orderDtoList.add(orderDto3);

        when(orderRepository.getAllOrderDtoListByManagerId(manager.getId())).thenReturn(orderDtoList);
        when(managerRepository.getManagerDtoById(manager.getId())).thenReturn(any());

        orderDtoList.forEach(orderDto -> {
            when(orderItemRepository.getListOrderItemDtoByOrderId(orderDto.getId())).thenReturn(new ArrayList<>());
            ClientDto clientDto = new ClientDto();
            clientDto.setId(orderDto.getId() + 10);
            when(orderRepository.getClientIdByOrderId(orderDto.getId())).thenReturn(clientDto.getId());
            when(clientRepository.getClientDtoFromClientWithId(clientDto.getId())).thenReturn(clientDto);
        });

        assertEquals(orderService.getAllOrderDtoListByManagerId(manager.getId()), orderDtoList);

        verify(orderRepository, times(1)).getAllOrderDtoListByManagerId(manager.getId());
        verify(managerRepository, times(1)).getManagerDtoById(manager.getId());
        verify(orderItemRepository, times(3)).getListOrderItemDtoByOrderId(any());
        verify(orderRepository, times(3)).getClientIdByOrderId(any());
        verify(clientRepository, times(3)).getClientDtoFromClientWithId(any());
    }
}
