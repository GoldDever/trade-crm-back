package ru.javamentor.service.product;

public interface ReserveProductService {
    String removeProductReserve(String orderId, String productId, String productCount);
}
