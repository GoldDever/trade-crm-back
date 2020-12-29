package ru.javamentor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javamentor.dto.product.ProductDto;
import ru.javamentor.dto.product.ProductPostDto;
import ru.javamentor.dto.product.SupplierDto;
import ru.javamentor.model.product.Product;
import ru.javamentor.model.product.Supplier;
import ru.javamentor.repository.product.ManufacturerRepository;
import ru.javamentor.repository.product.ProductRepository;
import ru.javamentor.repository.product.SupplierRepository;
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
    private SupplierRepository supplierRepository;

    public ProductServiceImpl() {}

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,
                              ManufacturerRepository manufacturerRepository,
                              UnitRepository unitRepository,
                              SupplierRepository supplierRepository) {
        this.productRepository = productRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.unitRepository = unitRepository;
        this.supplierRepository = supplierRepository;
    }

    /**
     * метод сохранения нового продукта
     *
     * @param dto экземпляр нового продукта
     */
    @Override
    public void saveProduct(ProductPostDto dto) {
        Product product = new Product();
        List<Supplier> finalList = new ArrayList<>();
        List<SupplierDto> tmpList = dto.getSupplierDto();

        for (int i = 0; i < dto.getSupplierDto().size(); i++) {
            finalList.add(new Supplier(tmpList.get(i).getId(), tmpList.get(i).getName()));
        }
        product.setProductName(dto.getProductName());
        product.setMadeCountry(dto.getMadeCountry());
        product.setManufacturer(manufacturerRepository.getOne(dto.getManufacturerDto().getId()));
        product.setSuppliers(new HashSet<>(finalList));
        product.setArticle(dto.getArticle());
        product.setPurchasePrice(BigDecimal.valueOf(dto.getPurchasePrice()));
        product.setPrice(BigDecimal.valueOf(dto.getPrice()));
        product.setMargin(BigDecimal.valueOf(dto.getMargin()));
        product.setUnit(unitRepository.getOne(dto.getUnitDto().getId()));
        product.setPackagingCount(dto.getPackagingCount());
        product.setIdFromErp(dto.getIdFromErp());

        productRepository.save(product);
    }

    /**
     * метод изменения продукта
     *
     * @param dto экземпляр изменяемого продукта
     */
    @Override
    public void updateProduct(ProductDto dto) {
        Product product = new Product();
        List<Supplier> finalList = new ArrayList<>();
        List<SupplierDto> tmpList = dto.getSupplierDto();

        for (int i = 0; i < dto.getSupplierDto().size(); i++) {
            finalList.add(new Supplier(tmpList.get(i).getId(), tmpList.get(i).getName()));
        }

        product.setId(dto.getId());
        product.setProductName(dto.getProductName());
        product.setMadeCountry(dto.getMadeCountry());
        product.setManufacturer(manufacturerRepository.getOne(dto.getManufacturerDto().getId()));
        product.setSuppliers(new HashSet<>(finalList));
        product.setArticle(dto.getArticle());
        product.setPurchasePrice(BigDecimal.valueOf(dto.getPurchasePrice()));
        product.setPrice(BigDecimal.valueOf(dto.getPrice()));
        product.setMargin(BigDecimal.valueOf(dto.getMargin()));
        product.setUnit(unitRepository.getOne(dto.getUnitDto().getId()));
        product.setPackagingCount(dto.getPackagingCount());
        product.setIdFromErp(dto.getIdFromErp());

        productRepository.saveAndFlush(product);
    }
}



















