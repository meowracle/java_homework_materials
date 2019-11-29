package com.codegym.services;

import com.codegym.models.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SupplierService {
    Page<Supplier> findAll(Pageable pageable);

    Supplier findById(Long id);

    void save (Supplier supplier);

    void remove (Long id);
}
