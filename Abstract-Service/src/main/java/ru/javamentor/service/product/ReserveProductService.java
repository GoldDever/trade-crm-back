package ru.javamentor.service.product;

import ru.javamentor.model.product.ReserveProduct;

public interface ReserveProductService {
    String saveProductReserve(Long orderId, Long productId, Integer productCount);
}
