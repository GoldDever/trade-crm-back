package ru.javamentor.init.product;

import org.springframework.stereotype.Component;
import ru.javamentor.model.product.ReserveProduct;
import ru.javamentor.repository.order.OrderRepository;
import ru.javamentor.repository.product.ProductRepository;
import ru.javamentor.repository.product.ReserveProductRepository;
@Component
public class InitReserveProductService {
    private final OrderRepository orderRepository;
    private final ReserveProductRepository reserveProductRepository;
    private final ProductRepository productRepository;

    public InitReserveProductService(OrderRepository orderRepository, ReserveProductRepository reserveProductRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.reserveProductRepository = reserveProductRepository;
        this.productRepository = productRepository;
    }
    public void initReserveProduct() {
        ReserveProduct reserveProduct = new ReserveProduct();
        reserveProduct.setId(1L);
        reserveProduct.setProductCount(23);
        reserveProduct.setProduct(productRepository.findById(1L).get());
        reserveProduct.setOrder(orderRepository.findById(1L).get());
        reserveProductRepository.save(reserveProduct);

        ReserveProduct reserveProduct2 = new ReserveProduct();
        reserveProduct2.setId(2L);
        reserveProduct2.setProductCount(15);
        reserveProduct2.setProduct(productRepository.findById(2L).get());
        reserveProduct2.setOrder(orderRepository.findById(2L).get());
        reserveProductRepository.save(reserveProduct2);
    }
}
