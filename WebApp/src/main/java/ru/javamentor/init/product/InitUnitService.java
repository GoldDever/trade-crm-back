package ru.javamentor.init.product;

import org.springframework.stereotype.Component;
import ru.javamentor.model.product.Unit;
import ru.javamentor.repository.product.UnitRepository;

@Component
public class InitUnitService {

    private final UnitRepository unitRepository;

    public InitUnitService(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    public void initUnit() {
        Unit unit1 = new Unit();
        unit1.setUnitName("кг");
        unitRepository.save(unit1);

        Unit unit2 = new Unit();
        unit2.setUnitName("шт");
        unitRepository.save(unit2);

    }
}
