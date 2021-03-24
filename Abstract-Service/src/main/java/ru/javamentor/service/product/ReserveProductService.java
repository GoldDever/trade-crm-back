package ru.javamentor.service.product;


import ru.javamentor.dto.product.ReserveProductDto;

import java.util.List;

public interface ReserveProductService {
    void removeOrderReserve(Long orderId);

    String removeProductReserve(Long orderId, Long productId, Integer productCount);

    String saveProductReserve(Long orderId, Long productId, Integer productCount);

    String addReserveByOrder(Long orderId);

    Integer getCountReservedProductByOrderIdAndProductId(Long orderId, Long productId);

    boolean isAllProductReservedByOrder(Long orderId);

    List<ReserveProductDto>  getListReserveProductDtoByOrderIdAndProductId(Long orderId, Long productId);

    List<ReserveProductDto> getListReserveProductDtoByProductId(Long productId);
}
