package ru.javamentor.service.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javamentor.dto.product.ProductPostDto;
import ru.javamentor.dto.product.SupplierDto;
import ru.javamentor.repository.product.ManufacturerRepository;
import ru.javamentor.repository.product.ProductCategoryRepository;
import ru.javamentor.repository.product.ProductRepository;
import ru.javamentor.repository.product.UnitRepository;
import ru.javamentor.model.product.Product;
import ru.javamentor.model.product.Supplier;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;


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
            finalList.add(new Supplier(tmp.getId(), tmp.getName(), tmp.idFromErp));
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
            finalList.add(new Supplier(tmp.getId(), tmp.getName(), tmp.getIdFromErp()));
        }

        String idFromErp=productPostDto.getIdFromErp();
        Product updateProduct=productRepository.findByIdFromErp(idFromErp);
        updateProduct.setProductCount(productPostDto.getProductCount());
        updateProduct.setProductName(productPostDto.getProductName());
        updateProduct.setMadeCountry(productPostDto.getMadeCountry());
        updateProduct.setManufacturer(manufacturerRepository.findById(productPostDto.getManufacturerDto().getId()).orElseThrow(()-> new NoSuchElementException("Manufacturer c idFromErp " + idFromErp + " не найден")));
        updateProduct.setSuppliers(new HashSet<>(finalList));
        updateProduct.setArticle(productPostDto.getArticle());
        updateProduct.setPurchasePrice(BigDecimal.valueOf(productPostDto.getPurchasePrice()));
        updateProduct.setPrice(BigDecimal.valueOf(productPostDto.getPrice()));
        updateProduct.setMargin(BigDecimal.valueOf(productPostDto.getMargin()));
        updateProduct.setUnit(unitRepository.findById(productPostDto.getUnitDto().getId()).orElseThrow(()-> new NoSuchElementException("Unit c idFromErp " + idFromErp + " не найден")));
        updateProduct.setPackagingCount(productPostDto.getPackagingCount());
        updateProduct.setIdFromErp(productPostDto.getIdFromErp());
        updateProduct.setProductCategory(productCategoryRepository.findById(productPostDto.getProductCategory().getId()).orElseThrow(()-> new NoSuchElementException ("ProductCategory с idFromErp " + idFromErp + " не найден")));
        productRepository.save(updateProduct);


    }

}
