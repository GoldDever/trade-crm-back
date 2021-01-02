package ru.javamentor.service.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javamentor.dto.product.ProductDto;
import ru.javamentor.dto.product.ProductPostDto;
import ru.javamentor.dto.product.SupplierDto;
import ru.javamentor.model.product.Product;
import ru.javamentor.model.product.Supplier;
import ru.javamentor.repository.product.ManufacturerRepository;
import ru.javamentor.repository.product.ProductRepository;
import ru.javamentor.repository.product.UnitRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Implement-Service для Продукта
 */
@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private ManufacturerRepository manufacturerRepository;
    private UnitRepository unitRepository;

    public ProductServiceImpl() {}

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,
                              ManufacturerRepository manufacturerRepository,
                              UnitRepository unitRepository) {
        this.productRepository = productRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.unitRepository = unitRepository;
    }

    /**
     * метод сохранения нового продукта
     *
     * @param dto экземпляр нового продукта
     */
    @Override
    public void saveProduct(ProductPostDto dto) {
        List<Supplier> finalList = new ArrayList<>();

        for (SupplierDto tmp : dto.getSupplierDto()) {
            finalList.add(new Supplier(tmp.getId(), tmp.getName()));
        }

        Product product = new Product(
                dto.getProductName(),
                dto.getMadeCountry(),
                manufacturerRepository.findById(dto.getManufacturerDto().getId()).orElseThrow(),
                new HashSet<>(finalList),
                dto.getArticle(),
                BigDecimal.valueOf(dto.getPurchasePrice()),
                BigDecimal.valueOf(dto.getPrice()),
                BigDecimal.valueOf(dto.getMargin()),
                unitRepository.findById(dto.getUnitDto().getId()).orElseThrow(),
                dto.getPackagingCount(),
                dto.getIdFromErp()
        );
        
        productRepository.save(product);
    }

    /**
     * метод изменения продукта
     *
     * @param dto экземпляр изменяемого продукта
     */
    @Override
    public void updateProduct(ProductDto dto) {
        List<Supplier> finalList = new ArrayList<>();

        for (SupplierDto tmp : dto.getSupplierDto()) {
            finalList.add(new Supplier(tmp.getId(), tmp.getName()));
        }

        Product product = new Product(
                dto.getId(),
                dto.getProductName(),
                dto.getMadeCountry(),
                manufacturerRepository.findById(dto.getManufacturerDto().getId()).orElseThrow(),
                new HashSet<>(finalList),
                dto.getArticle(),
                BigDecimal.valueOf(dto.getPurchasePrice()),
                BigDecimal.valueOf(dto.getPrice()),
                BigDecimal.valueOf(dto.getMargin()),
                unitRepository.findById(dto.getUnitDto().getId()).orElseThrow(),
                dto.getPackagingCount(),
                dto.getIdFromErp()
        );

        productRepository.saveAndFlush(product);
    }
}
