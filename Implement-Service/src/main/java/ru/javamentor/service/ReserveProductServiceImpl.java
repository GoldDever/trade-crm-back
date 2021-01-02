package ru.javamentor.service;

import org.springframework.stereotype.Service;
import ru.javamentor.model.product.ReserveProduct;
import ru.javamentor.repository.order.ReserveProductRepository;

@Service
public class ReserveProductServiceImpl implements ReserveProductService {

    private final ReserveProductRepository reserveProductRepository;

    public ReserveProductServiceImpl(ReserveProductRepository reserveProductRepository) {
        this.reserveProductRepository = reserveProductRepository;
    }

    @Override
    public String removeProductReserve(String orderId, String productId, String productCount) {
        Long orderIdL = Long.valueOf(orderId);
        Long productIdL = Long.valueOf(productId);
        ReserveProduct reserveProduct = reserveProductRepository.findByOrder_Id_AndProduct_Id(orderIdL, productIdL);

        String response;
        Integer productCountInt = Integer.valueOf(productCount);
        if (reserveProduct.getProductCount().equals(productCountInt)) {
            reserveProductRepository.delete(reserveProduct);
            response = "Резерв полностью удален.";
        } else {
            Integer reserveProductDelta = reserveProduct.getProductCount() - productCountInt;
            reserveProduct.setProductCount(reserveProductDelta);
            response = String.format("Товар в количестве %s снят с резерва.", reserveProductDelta);
        }

        return response;
    }
}
