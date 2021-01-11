package ru.javamentor.service.product;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javamentor.model.product.ReserveProduct;
import ru.javamentor.repository.order.OrderRepository;
import ru.javamentor.repository.product.ProductRepository;
import ru.javamentor.repository.product.ReserveProductRepository;

import java.util.List;

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

    /**
     * Метод сохранения резерва
     *
     * @param orderId - id Order
     * @param productId - id продукта по которому сохраняется резерв
     * @param productCount - количество продукта, которое необходимо зарезервировать
     * @return - сообщение о результатате резервирования продукта
     */
    @Override
    @Transactional
    public synchronized String saveProductReserve(Long orderId, Long productId, Integer productCount) {
        String response;
        if (reserveProductRepository.countReserveProducts(productId) >= productCount) {
            reserveProductRepository.save(new ReserveProduct(
                    orderId,
                    productRepository.findProductById(productId),
                    orderRepository.findOrderById(orderId),
                    productCount));
            response = "Товар зарезервирован";
        } else {
            response = String.format("Количество товара доступного для резерва %s.",
                    reserveProductRepository.countReserveProducts(productId));
        }
        return response;
    }
}
