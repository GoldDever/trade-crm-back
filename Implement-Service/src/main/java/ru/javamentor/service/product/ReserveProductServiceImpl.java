package ru.javamentor.service.product;

import org.springframework.stereotype.Service;
import ru.javamentor.model.product.ReserveProduct;
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
}
