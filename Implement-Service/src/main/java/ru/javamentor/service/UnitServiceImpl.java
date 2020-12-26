package ru.javamentor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javamentor.model.product.Unit;
import ru.javamentor.repository.order.UnitRepository;

@Service
public class UnitServiceImpl implements UnitService {

    @Autowired
    UnitRepository unitRepository;

    @Override
    public Unit getUnitById(Long id) {
        return unitRepository.findById(id).orElseThrow();
    }
}
