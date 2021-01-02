package ru.javamentor.service;

public interface ReserveProductService {
    String removeProductReserve(String orderId, String productId, String productCount);
}
