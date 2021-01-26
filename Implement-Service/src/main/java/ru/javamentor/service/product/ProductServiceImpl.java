package ru.javamentor.service.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javamentor.dto.product.ProductDto;
import ru.javamentor.dto.product.ProductPostDto;
import ru.javamentor.dto.product.SupplierDto;
import ru.javamentor.model.product.Product;
import ru.javamentor.model.product.Supplier;
import ru.javamentor.repository.product.ManufacturerRepository;
import ru.javamentor.repository.product.ProductCategoryRepository;
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
    private ProductCategoryRepository productCategoryRepository;
    private SupplierRepository supplierRepository;

    public ProductServiceImpl() {}

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,
                              ManufacturerRepository manufacturerRepository,
                              UnitRepository unitRepository,
                              ProductCategoryRepository productCategoryRepository,
                              SupplierRepository supplierRepository) {
        this.productRepository = productRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.unitRepository = unitRepository;
        this.productCategoryRepository = productCategoryRepository;
        this.supplierRepository = supplierRepository;
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
                dto.getProductCount(),
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
                dto.getIdFromErp(),
                productCategoryRepository.findById(dto.getProductCategory().getId()).orElseThrow()

        );
        
        productRepository.save(product);
    }

    /**
     * Метод обновляет продукт
     *
     * @param productPostDto - продукт
     */
    @Override
    public void updateProduct(ProductPostDto productPostDto) {
        //TODO написать реализацию
    }

    /**
     * Метод возвращаем productDto по product id
     *
     * @param productId - id продукта
     * @return - productDto
     */
    @Override
    public ProductDto getProductDtoByProductId(Long productId) {
        ProductDto dto = productRepository.findProductDtoByProductId(productId);
        Long manufacturerId = productRepository.findManufacturerIdByProductId(productId);
        Long unitId = productRepository.findUnitIdByProductId(productId);
        List<Long> supplierIdList = productRepository.findListSupplierIdByProductId(productId);
        dto.setManufacturerDto(manufacturerRepository.findManufacturerDtoByManufacturerId(manufacturerId));
        dto.setSupplierDto(supplierRepository.findSupplierDtoBySupplierId(supplierIdList));
        dto.setUnitDto(unitRepository.findUnitDtoByUnitId(unitId));
        dto.setProductCategory(productCategoryRepository.findProductCategoryByProductId(productId).getCategoryName());

        return dto;
    }

    /**
     * Метод проверяет существования productId в базе
     * @param productId - id продукта
     * @return - есть или нет productId в базе
     */
    @Override
    public boolean isProductIdExists(Long productId) {
        return productRepository.existsById(productId);
    }


}
