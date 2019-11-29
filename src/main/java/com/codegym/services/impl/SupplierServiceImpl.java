package com.codegym.services.impl;

import com.codegym.models.Supplier;
import com.codegym.repositories.SupplierRepository;
import com.codegym.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class SupplierServiceImpl implements SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;


    @Override
    public Page<Supplier> findAll(Pageable pageable) {
        return supplierRepository.findAll(pageable);
    }

    @Override
    public Supplier findById(Long id) {
        return supplierRepository.findOne(id);
    }

    @Override
    public void save(Supplier supplier) {
        supplierRepository.save(supplier);
    }

    @Override
    public void remove(Long id) {
        supplierRepository.delete(id);
    }
}
