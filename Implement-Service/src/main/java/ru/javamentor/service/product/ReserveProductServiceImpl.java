package ru.javamentor.service.product;

import org.springframework.stereotype.Service;
import ru.javamentor.repository.product.ReserveProductRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


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
     * @return - код ответа для проверки на наличие в резерва в БД
     */
    @Transactional
    @Override
    public Integer removeProductReserve(Long orderId, Long productId, Integer productCount) {
        int code;

        List<Integer> reserveProductCountList
                = new ArrayList<>(reserveProductRepository.getReserveProductCounts(orderId, productId));

        Integer countReserveProductSum = reserveProductRepository.getSumOfReserveProductCounts(orderId, productId);

        if (reserveProductCountList.isEmpty()) {
            code = 0;
        } else if (countReserveProductSum.equals(productCount) && reserveProductCountList.size() == 1) {
            reserveProductRepository.deleteReserve(orderId, productId, productCount);
            code = 1;
        } else {
            for (Integer count : reserveProductCountList) {
                if (count < productCount) {
                    reserveProductRepository.deleteReserve(orderId, productId, count);
                } else {
                    reserveProductRepository.updateReserveProductCount(orderId, productId, productCount, count);
                }
            }
            code = 2;
        }

        return code;
    }
}
