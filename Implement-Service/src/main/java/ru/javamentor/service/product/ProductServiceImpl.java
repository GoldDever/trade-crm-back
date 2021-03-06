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

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;


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

    Logger logger = Logger.getLogger(ProductService.class.getName());

    public ProductServiceImpl() {
    }

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
            finalList.add(new Supplier(tmp.getId(), tmp.getName(), tmp.idFromErp));
        }

        Product product = new Product(
                dto.getProductCount(),
                dto.getProductName(),
                dto.getMadeCountry(),
                manufacturerRepository.findById(dto.getManufacturerDto().getId()).orElseThrow(),
                new HashSet<>(finalList),
                dto.getArticle(),
                BigDecimal.valueOf(dto.getMinMargin()),
                BigDecimal.valueOf(dto.getPrice()),
                BigDecimal.valueOf(dto.getStandardMargin()),
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

        String idFromErp = productPostDto.getIdFromErp();
        Product updateProduct = productRepository.findByIdFromErp(idFromErp);
        updateProduct.setProductCount(productPostDto.getProductCount());
        updateProduct.setProductName(productPostDto.getProductName());
        updateProduct.setMadeCountry(productPostDto.getMadeCountry());
        updateProduct.setManufacturer(manufacturerRepository.findById(productPostDto.getManufacturerDto().getId()).orElseThrow(() -> new NoSuchElementException("Manufacturer c idFromErp " + idFromErp + " не найден")));
        updateProduct.setSuppliers(new HashSet<>(finalList));
        updateProduct.setArticle(productPostDto.getArticle());
        updateProduct.setMinMargin(BigDecimal.valueOf(productPostDto.getMinMargin()));
        updateProduct.setPrice(BigDecimal.valueOf(productPostDto.getPrice()));
        updateProduct.setStandardMargin(BigDecimal.valueOf(productPostDto.getStandardMargin()));
        updateProduct.setUnit(unitRepository.findById(productPostDto.getUnitDto().getId()).orElseThrow(() -> new NoSuchElementException("Unit c idFromErp " + idFromErp + " не найден")));
        updateProduct.setPackagingCount(productPostDto.getPackagingCount());
        updateProduct.setIdFromErp(productPostDto.getIdFromErp());
        updateProduct.setProductCategory(productCategoryRepository.findById(productPostDto.getProductCategory().getId()).orElseThrow(() -> new NoSuchElementException("ProductCategory с idFromErp " + idFromErp + " не найден")));
        productRepository.save(updateProduct);


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
        dto.setManufacturer(manufacturerRepository.findManufacturerDtoByManufacturerId(manufacturerId));
        dto.setSupplierDto(supplierRepository.findSupplierDtoBySupplierId(supplierIdList));
        dto.setUnit(unitRepository.findUnitDtoByUnitId(unitId));
        dto.setProductCategory(productCategoryRepository.findProductCategoryByProductId(productId));

        return dto;
    }

    /**
     * Метод проверяет существования productId в базе
     *
     * @param productId - id продукта
     * @return - есть или нет productId в базе
     */
    @Override
    public boolean isProductIdExists(Long productId) {
        return productRepository.existsById(productId);
    }

    /**
     * Метод проверяет существования idFromErp в базе
     *
     * @param idFromErp - idFromErp продукта
     * @return - есть или нет idFromErp в базе
     */
    @Override
    public Product getProductByIdFromErp(String idFromErp) {
        return productRepository.findByIdFromErp(idFromErp);
    }

    /**
     * Метод обновляет url изображения продукта
     *
     * @param product  - продукт
     * @param imageUrl - url изображения
     */
    @Override
    public void setProductImageUrl(Product product, String imageUrl) {
        product.setImageUrl(imageUrl);
        productRepository.save(product);
    }

    /**
     * Метод возвращает результат поиска по вхождени в наименование товара.
     * Для пустой или отсутствующей строки поиска вернется список всех товаров.
     *
     * @param search - строка поиска
     * @return - список найденного товара
     */
    @Override
    public List<ProductDto> getProductListBySearch(String search) {

        List<ProductDto> dtoList;

        if (search == null || search.isEmpty()) {
            dtoList = productRepository.findAllProductDto();
        } else {
            dtoList = productRepository.findByProductNameIgnoreCaseContaining(search);
        }

        dtoList.forEach(productDto -> {
            Long manufacturerId = productRepository.findManufacturerIdByProductId(productDto.getId());
            Long unitId = productRepository.findUnitIdByProductId(productDto.getId());
            List<Long> supplierIdList = productRepository.findListSupplierIdByProductId(productDto.getId());
            productDto.setManufacturer(manufacturerRepository.findManufacturerDtoByManufacturerId(manufacturerId));
            productDto.setSupplierDto(supplierRepository.findSupplierDtoBySupplierId(supplierIdList));
            productDto.setUnit(unitRepository.findUnitDtoByUnitId(unitId));
            productDto.setProductCategory(productCategoryRepository.findProductCategoryByProductId(productDto.getId()));
        });

        return dtoList;
    }

    /**
     * Метод возвращает массив байтов картинки продукта.
     *
     * @param productId - id продукта
     * @return - массив байтов картинки
     */

    @Override
    public byte[] getProductImage(Long productId) {
        String productImageUrl = productRepository.findImageUrlByProductId(productId);
        try {
            Path pathImageUrl = Paths.get(productImageUrl);
            return Files.readAllBytes(pathImageUrl);
        } catch (IOException e) {
            logger.log(Level.WARNING, e.getMessage());
            throw new ProductServiceException("Не удалось прочитать изображение!");
        }
    }
}


