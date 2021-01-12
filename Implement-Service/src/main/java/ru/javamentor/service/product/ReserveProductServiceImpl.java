package ru.javamentor.service.product;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javamentor.model.order.Order;
import ru.javamentor.model.product.Product;
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

    /**
     * Метод сохранения резерва
     *
     * @param orderId - id Order
     * @param productId - id продукта по которому сохраняется резерв
     * @param productCount - количество продукта, которое необходимо зарезервировать
     * @return - сообщение о результате резервирования продукта
     */
    @Override
    @Transactional
    public synchronized String saveProductReserve(Long orderId, Long productId, Integer productCount) {
        Product product = productRepository.findProductById(productId);
        Order order = orderRepository.findOrderById(orderId);
        if (reserveProductRepository.existsByProductId(productId)) {
            Integer availableReserveCount = reserveProductRepository.countReserveProducts(productId);
            if (availableReserveCount >= productCount) {
                reserveProductRepository.save(new ReserveProduct(product, order, productCount));
                return "Товар зарезервирован";
            } else {
                return String.format("Количество товара доступное для резерва %s.", availableReserveCount);
            }
        } else {
            if (product.getProductCount() >= productCount) {
                reserveProductRepository.save(new ReserveProduct(product, order, productCount));
                return "Товар зарезервирован";
            } else {
                return String.format("Количество товара доступное для резерва %s.", product.getProductCount());
            }
        }
    }
}
