package ru.javamentor.init.product;

import org.springframework.stereotype.Component;
import ru.javamentor.model.product.Supplier;
import ru.javamentor.repository.product.SupplierRepository;

@Component
public class InitSupplierService {

    private final SupplierRepository supplierRepository;

    public InitSupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public void initSupplier() {
        createSupplier("Полигон");
        createSupplier("Партком");
        createSupplier("Бригадир");
    }

    private void createSupplier(String name) {
        Supplier supplier = new Supplier();
        supplier.setName(name);
        supplierRepository.save(supplier);
    }
}
