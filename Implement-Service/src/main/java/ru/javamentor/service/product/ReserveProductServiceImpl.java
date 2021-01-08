package ru.javamentor.service.product;

import org.springframework.stereotype.Service;
import ru.javamentor.repository.product.ReserveProductRepository;

import javax.transaction.Transactional;


@Service
public class ReserveProductServiceImpl implements ReserveProductService {

    private final ReserveProductRepository reserveProductRepository;

    public ReserveProductServiceImpl(ReserveProductRepository reserveProductRepository) {
        this.reserveProductRepository = reserveProductRepository;
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
     * @param orderId      - id заказа
     * @param productId    - id продукта
     * @param productCount - количество удалеямого продукта из резерва
     * @return - HTTP ответ с BODY
     */
    @Transactional
    @Override
    public String removeProductReserve(Long orderId, Long productId, Integer productCount) {
        Integer reserveProductCount = reserveProductRepository.getReserveProductCount(orderId, productId);

        String response;
        if (reserveProductCount.equals(productCount)) {
            reserveProductRepository.deleteReserve(orderId, productId);
            response = "Резерв полностью удален.";
        } else {
            reserveProductRepository.updateReserveProductCount(productCount, orderId, productId);
            response = String.format("Товар в количестве %s снят с резерва.", reserveProductCount - productCount);
        }

        return response;
    }
}
