package ru.javamentor.service.product;

import org.springframework.stereotype.Service;
import ru.javamentor.model.order.Order;
import ru.javamentor.model.order.OrderItem;
import ru.javamentor.model.product.Product;
import ru.javamentor.model.product.ReserveProduct;
import ru.javamentor.repository.order.OrderRepository;
import ru.javamentor.repository.product.ProductRepository;
import ru.javamentor.repository.product.ReserveProductRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
public class ReserveProductServiceImpl implements ReserveProductService {

    private final ReserveProductRepository reserveProductRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public ReserveProductServiceImpl(ReserveProductRepository reserveProductRepository, OrderRepository orderRepository, ProductRepository productRepository) {
        this.reserveProductRepository = reserveProductRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    /**
     * Метод поиска и удаления зарезервированных заказов
     *
     * @param orderId - id заказа
     */
    @Transactional
    @Override
    public void removeOrderReserve(Long orderId) {
        reserveProductRepository.deleteByOrderId(orderId);
    }

    /**
     * Метод удаляет продукт из резерва.
     * Если количество резерва равно входному параметру.
     * Иначе сохраняет новое значение.
     *
     * @param orderId - id заказа
     * @param productId - id продукта
     * @param productCount - количество удаляемого продукта из резерва
     * @return - код ответа для проверки на наличие в резерва в БД
     */
    @Transactional
    @Override
    public String removeProductReserve(Long orderId, Long productId, Integer productCount) {

        Integer remainder = productCount;

        List<ReserveProduct> reserveProductCountList
                = new ArrayList<>(reserveProductRepository.getReserveProductList(orderId, productId));

        Integer countReserveProductSum = reserveProductRepository.getSumOfReserveProductCounts(orderId, productId);

        if (reserveProductCountList.isEmpty()) {
            return "Резерв не найден!";
        } else if (countReserveProductSum < productCount){
            return "Нет достаточного количества резерва";
        } else {
            for (ReserveProduct reserveProduct : reserveProductCountList) {
                if (reserveProduct.getProductCount() <= remainder) {
                    reserveProductRepository.deleteReserve(reserveProduct.getId(), orderId, productId, reserveProduct.getProductCount());
                    remainder = remainder - reserveProduct.getProductCount();
                } else {
                    reserveProductRepository.updateReserveProductCount(reserveProduct.getId(), orderId, productId, remainder, reserveProduct.getProductCount());
                    break;
                }
            }
            return String.format("Товар в количестве %s снят с резерва.", productCount);
        }
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

    /**
     * Метод сохранения резерва по orderId
     *
     * @param orderId - id заказа
     * @return - сообщение о результате резервирования продукта
     */
    @Transactional
    @Override
    public synchronized String addReserveByOrder(Long orderId) {

        StringBuilder result = new StringBuilder();

        List<OrderItem> orderItems = new ArrayList<>(reserveProductRepository.getOrderItemListByOrderId(orderId));

        for(OrderItem item: orderItems){
            if(reserveProductRepository.countReserveProducts(item.getProduct().getId()) >= item.getProductCount()){
                reserveProductRepository.save(new ReserveProduct(item.getProduct(), item.getOrder(), item.getProductCount()));
            }
            else{
                result.append(item.getProduct().getProductName());
                result.append("\n");
            }
        }
        return String.valueOf(result);
    }

    /**
     *
     * @param orderId - id заказа
     * @param productId - id продукта
     * @return - количество зарезервированных продуктов
     */
    @Transactional
    @Override
    public Integer getCountReservedProductByOrderIdAndProductId(Long orderId, Long productId) {
        return reserveProductRepository.getCountReservedProduct(orderId, productId);
    }
}
