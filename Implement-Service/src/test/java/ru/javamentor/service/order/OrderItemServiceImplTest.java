package ru.javamentor.service.order;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.javamentor.model.order.Order;
import ru.javamentor.model.order.OrderItem;
import ru.javamentor.model.product.Product;
import ru.javamentor.repository.order.OrderItemRepository;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class OrderItemServiceImplTest {

    @Mock
    private OrderItemRepository orderItemRepository;
    @Mock
    private Product product;
    @Mock
    private Order order;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void saveOrderItem() {
        OrderItem orderItem2 = new OrderItem(
                10L,
                "InvoiceIssued",
                12,
                product,
                order,
                1,
                BigDecimal.ONE
        );
        when(orderItemRepository.save(orderItem2)).thenReturn(orderItem2);
        assertEquals(orderItem2, orderItemRepository.save(orderItem2));
    }

    @Test
    public void editOrderItem() {
        OrderItemServiceImpl mock = mock(OrderItemServiceImpl.class);
        mock.editOrderItem(anyLong(), anyInt());
        verify(mock).editOrderItem(0L, 0);
    }
}