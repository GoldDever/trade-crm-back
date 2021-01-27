package ru.javamentor.service.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javamentor.dto.product.ProductDto;
import ru.javamentor.dto.product.ProductPostDto;
import ru.javamentor.dto.product.SupplierDto;
import ru.javamentor.model.product.*;
import ru.javamentor.repository.product.ManufacturerRepository;
import ru.javamentor.repository.product.ProductCategoryRepository;
import ru.javamentor.repository.product.ProductRepository;
import ru.javamentor.repository.product.UnitRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.logging.*;
import org.springframework.web.filter.GenericFilterBean;
import ru.javamentor.service.JwtUserDetailsService;

import javax.persistence.EntityNotFoundException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Implement-Service для Продукта
 */
@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private ManufacturerRepository manufacturerRepository;
    private UnitRepository unitRepository;
    private ProductCategoryRepository productCategoryRepository;

    private final static Logger logger =  Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);


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
        try {
            product.setManufacturer(manufacturerRepository.findById(productPostDto.getManufacturerDto().getId()).orElseThrow(()-> new Exception (idFromErp)));
        } catch (Exception e) {
            //e.printStackTrace();
            logger.warning("Не заполнено поле для обновления" + e);
            return;
        }
        try {
        product.setUnit(unitRepository.findById(productPostDto.getUnitDto().getId()).orElseThrow(()-> new Exception (idFromErp)));
    } catch (Exception e) {
        //e.printStackTrace();
        logger.warning("Не заполнено поле для обновления" + e);
        return;
    };
        try {
        product.setProductCategory(productCategoryRepository.findById(productPostDto.getProductCategory().getId()).orElseThrow(()-> new Exception (idFromErp)));
    } catch (Exception e) {
        //e.printStackTrace();
        logger.warning("Не заполнено поле для обновления" + e);
        return;
    };
        product (productPostDto.getProductCount(),
                productPostDto.getProductName(),
                productPostDto.getMadeCountry(),
                //manufacturerRepository.findById(productPostDto.getManufacturerDto().getId()).orElseThrow(),
                new HashSet<>(finalList),
                productPostDto.getArticle(),
                BigDecimal.valueOf(productPostDto.getPurchasePrice()),
                BigDecimal.valueOf(productPostDto.getPrice()),
                BigDecimal.valueOf(productPostDto.getMargin()),
                //unitRepository.findById(productPostDto.getUnitDto().getId()).orElseThrow(),
                productPostDto.getPackagingCount(),
                productPostDto.getIdFromErp()
                //productCategoryRepository.findById(productPostDto.getProductCategory().getId()).orElseThrow()
        );
//-> new EntityNotFoundException(String.format("No record found")))
        //-> new RuntimeException("instant can not be null"));

        productRepository.save(product);
    }

    private void product(Integer productCount, String productName, String madeCountry,
                         HashSet<Supplier> suppliers, String article, BigDecimal valueOf,
                         BigDecimal valueOf1, BigDecimal valueOf2, Integer packagingCount, String idFromErp) {
    }


}
