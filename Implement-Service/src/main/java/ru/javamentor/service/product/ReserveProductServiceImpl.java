package ru.javamentor.service.product;

import org.springframework.stereotype.Service;
import ru.javamentor.model.product.ReserveProduct;
import ru.javamentor.repository.order.OrderRepository;
import ru.javamentor.repository.product.ProductRepository;
import ru.javamentor.repository.product.ReserveProductRepository;

@Service
public class ReserveProductServiceImpl implements ReserveProductService{

    private final ReserveProductRepository reserveProductRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public ReserveProductServiceImpl(ReserveProductRepository reserveProductRepository, OrderRepository orderRepository, ProductRepository productRepository) {
        this.reserveProductRepository = reserveProductRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }



    @Override
    public void saveProductReserve(Long orderId, Long productId, Integer productCount) {

        ReserveProduct reserveProduct = new ReserveProduct(
                orderId,
                productRepository.findProductById(productId),
                orderRepository.findOrderById(orderId),
                productCount);

        reserveProductRepository.save(reserveProduct);
    }
}
