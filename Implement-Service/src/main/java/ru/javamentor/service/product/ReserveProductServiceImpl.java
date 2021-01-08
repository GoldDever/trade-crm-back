package ru.javamentor.service.product;

import org.springframework.stereotype.Service;
import ru.javamentor.model.product.ReserveProduct;
import ru.javamentor.repository.product.ReserveProductRepository;

@Service
public class ReserveProductServiceImpl implements ReserveProductService {

    private final ReserveProductRepository reserveProductRepository;

    public ReserveProductServiceImpl(ReserveProductRepository reserveProductRepository) {
        this.reserveProductRepository = reserveProductRepository;
    }

    /**
     * Метод удаляет продукт из резерва.
     * Если количество резерва равно входному параметру.
     * Иначе удаляет запрашиваемое количество.
     *
     * @param orderId      - id заказа
     * @param productId    - id продукта
     * @param productCount - количество удалеямого продукта из резерва
     * @return - HTTP ответ с BODY
     */
    @Override
    public String removeProductReserve(Long orderId, Long productId, Integer productCount) {
        ReserveProduct reserveProduct = reserveProductRepository.findByOrder_Id_AndProduct_Id(orderId, productId);

        String response;
        if (reserveProduct.getProductCount().equals(productCount)) {
            reserveProductRepository.delete(reserveProduct);
            response = "Резерв полностью удален.";
        } else {
            Integer reserveProductDelta = reserveProduct.getProductCount() - productCount;
            reserveProduct.setProductCount(reserveProductDelta);
            response = String.format("Товар в количестве %s снят с резерва.", reserveProductDelta);
        }

        return response;
    }
}
