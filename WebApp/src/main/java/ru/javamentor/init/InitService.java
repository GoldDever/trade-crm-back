package ru.javamentor.init;

import org.springframework.stereotype.Component;
import ru.javamentor.init.order.InitOrderApproveService;
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

import javax.annotation.PostConstruct;


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
    private final InitOrderApproveService initOrderApproveService;


    public InitService(InitOrderService initOrderService, InitOrderItemService initOrderItemService, InitManufacturerService initManufacturerService, InitUnitService initUnitService, InitReserveProductService initReserveProductService, InitSupplierService initSupplierService, InitProductCategoryService initProductCategoryService, InitProductService initProductService, InitRoleService initRoleService, InitUserService initUserService, InitOrderApproveService initOrderApproveService) {
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
        this.initOrderApproveService = initOrderApproveService;
    }
    @PostConstruct
    private void init() {
        initRoleService.initRole();
        initUserService.initUser();
        initManufacturerService.initManufacturer();
        initProductCategoryService.initProductCategory();
        initSupplierService.initSupplier();
        initUnitService.initUnit();
        initProductService.initProduct();
        initOrderService.initOrder();
        initOrderItemService.initOrderItem();
        initReserveProductService.initReserveProduct();
        initOrderApproveService.initOrderApprove();
    }


}
