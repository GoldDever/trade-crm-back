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
        Supplier supplier1 = new Supplier();
        supplier1.setName("Полигон");
        supplierRepository.save(supplier1);

        Supplier supplier2 = new Supplier();
        supplier2.setName("Партком");
        supplierRepository.save(supplier2);

        Supplier supplier3 = new Supplier();
        supplier3.setName("Бригадир");
        supplierRepository.save(supplier3);

    }
}
