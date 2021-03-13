package ru.javamentor.init.product;

import org.springframework.stereotype.Component;
import ru.javamentor.model.order.Order;
import ru.javamentor.model.product.Product;
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
        createReserveProduct(23, productRepository.findById(1L).get(), orderRepository.findById(1L).get());
        createReserveProduct(15, productRepository.findById(2L).get(), orderRepository.findById(2L).get());
        createReserveProduct(12, productRepository.findById(1L).get(), orderRepository.findById(2L).get());
    }

    private void createReserveProduct(int productCount, Product product, Order order) {
        ReserveProduct reserveProduct = new ReserveProduct();
        reserveProduct.setProductCount(productCount);
        reserveProduct.setProduct(product);
        reserveProduct.setOrder(order);
        reserveProductRepository.save(reserveProduct);
    }
}
