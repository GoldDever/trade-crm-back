package ru.javamentor.service.product;


public interface ReserveProductService {
    void removeOrderReserve(Long orderId);

    String removeProductReserve(Long orderId, Long productId, Integer productCount);

    String saveProductReserve(Long orderId, Long productId, Integer productCount);

    String addReserveByOrder(Long orderId);
}
