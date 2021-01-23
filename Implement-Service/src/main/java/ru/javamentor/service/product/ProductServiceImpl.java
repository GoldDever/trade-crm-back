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
import ru.javamentor.repository.product.UnitRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Implement-Service для Продукта
 */
@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private ManufacturerRepository manufacturerRepository;
    private UnitRepository unitRepository;
    private ProductCategoryRepository productCategoryRepository;

    public ProductServiceImpl() {}

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,
                              ManufacturerRepository manufacturerRepository,
                              UnitRepository unitRepository,
                                ProductCategoryRepository productCategoryRepository) {
        this.productRepository = productRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.unitRepository = unitRepository;
        this.productCategoryRepository = productCategoryRepository;
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
        List<Supplier> finalList = new ArrayList<>();
        for (SupplierDto tmp : productPostDto.getSupplierDto()) {
            finalList.add(new Supplier(tmp.getId(), tmp.getName()));
        }
        String idFromErp=productPostDto.getIdFromErp();

        Product product = productRepository.findProductByIdFromErp(idFromErp);
        product.setProductCount(productPostDto.getProductCount());
        product.setProductName(productPostDto.getProductName());
        product.setMadeCountry(productPostDto.getMadeCountry());
        product.setManufacturer(manufacturerRepository.findById(productPostDto.getManufacturerDto().getId()).orElseThrow());
        product.setSuppliers(new HashSet<>(finalList));
        product.setArticle(productPostDto.getArticle());
        product.setPurchasePrice(BigDecimal.valueOf(productPostDto.getPurchasePrice()));
        product.setPrice(BigDecimal.valueOf(productPostDto.getPrice()));
        product.setMargin(BigDecimal.valueOf(productPostDto.getMargin()));
        product.setUnit(unitRepository.findById(productPostDto.getUnitDto().getId()).orElseThrow());
        product.setPackagingCount(productPostDto.getPackagingCount());
        product.setIdFromErp(productPostDto.getIdFromErp());
        product.setProductCategory(productCategoryRepository.findById(productPostDto.getProductCategory().getId()).orElseThrow());

        productRepository.save(product);
        //TODO написать реализацию
    }
}
