package ru.javamentor.service.product;

import ru.javamentor.model.product.ReserveProduct;

public interface ReserveProductService {
    void saveProductReserve(Long orderId, Long productId, Integer productCount);

}
