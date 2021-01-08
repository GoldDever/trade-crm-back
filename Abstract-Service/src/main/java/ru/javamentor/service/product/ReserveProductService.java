package ru.javamentor.service.product;

public interface ReserveProductService {
    String removeProductReserve(Long orderId, Long productId, Integer productCount);
}
