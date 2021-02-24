package ru.javamentor.init;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.javamentor.init.order.InitOrderItemService;
import ru.javamentor.init.order.InitOrderService;
import ru.javamentor.init.product.InitManufacturerService;
import ru.javamentor.init.product.InitProductCategoryService;
import ru.javamentor.init.product.InitProductService;
import ru.javamentor.init.product.InitReserveProductService;
import ru.javamentor.init.product.InitSupplierService;
import ru.javamentor.init.product.InitUnitService;
import ru.javamentor.init.user.InitRoleService;
import ru.javamentor.init.user.InitUserService;
import ru.javamentor.model.product.Manufacturer;
import ru.javamentor.model.product.Product;
import ru.javamentor.model.product.ProductCategory;
import ru.javamentor.model.product.ReserveProduct;
import ru.javamentor.model.product.Supplier;
import ru.javamentor.model.product.Unit;
import ru.javamentor.model.user.Client;
import ru.javamentor.model.user.Manager;
import ru.javamentor.model.user.Role;
import ru.javamentor.model.user.User;
import ru.javamentor.repository.RoleRepository;
import ru.javamentor.repository.UserRepository;
import ru.javamentor.repository.order.OrderItemRepository;
import ru.javamentor.repository.order.OrderRepository;
import ru.javamentor.repository.product.ProductRepository;
import ru.javamentor.repository.product.ReserveProductRepository;
import ru.javamentor.repository.product.ManufacturerRepository;
import ru.javamentor.repository.product.SupplierRepository;
import ru.javamentor.repository.product.ProductCategoryRepository;
import ru.javamentor.repository.product.UnitRepository;
import ru.javamentor.repository.user.ClientRepository;
import ru.javamentor.repository.user.ManagerRepository;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Set;

@Component
public class InitService {

    private final InitOrderService initOrderService;
    private final InitOrderItemService initOrderItemService;
    private final InitManufacturerService initManufacturerService;
    private final InitUnitService initUnitService;
    private final InitReserveProductService initReserveProductService;
    private final InitSupplierService initSupplierService;
    private final InitProductCategoryService initProductCategoryService;
    private final InitProductService initProductService;
    private final InitRoleService initRoleService;
    private final InitUserService initUserService;


    public InitService(InitOrderService initOrderService, InitOrderItemService initOrderItemService, InitManufacturerService initManufacturerService, InitUnitService initUnitService, InitReserveProductService initReserveProductService, InitSupplierService initSupplierService, InitProductCategoryService initProductCategoryService, InitProductService initProductService, InitRoleService initRoleService, InitUserService initUserService) {
        this.initOrderService = initOrderService;
        this.initOrderItemService = initOrderItemService;
        this.initManufacturerService = initManufacturerService;
        this.initUnitService = initUnitService;
        this.initReserveProductService = initReserveProductService;
        this.initSupplierService = initSupplierService;
        this.initProductCategoryService = initProductCategoryService;
        this.initProductService = initProductService;
        this.initRoleService = initRoleService;
        this.initUserService = initUserService;
    }
    @PostConstruct
    private void init() {
        initRoleService.initRole();
        initUserService.initManager();
        initUserService.initClient();
        initUserService.initAdmin();
        initManufacturerService.initManufacturer();
        initProductCategoryService.initProductCategory();
        initSupplierService.initSupplier();
        initUnitService.initUnit();
        initProductService.initProduct();
        initOrderService.initOrder();
        initOrderItemService.initOrderItem();
        initReserveProductService.initReserveProduct();
    }


}
