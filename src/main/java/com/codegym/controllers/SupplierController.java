package com.codegym.controllers;

import com.codegym.models.Material;
import com.codegym.models.Supplier;
import com.codegym.services.MaterialService;
import com.codegym.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private MaterialService materialService;

    @GetMapping("/suppliers")
    public ModelAndView listSuppliers(@PageableDefault(size = 5, sort = "name") Pageable pageable){
        Page<Supplier> suppliers = supplierService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/supplier/list");
        modelAndView.addObject("suppliers", suppliers);
        return modelAndView;
    }

    @GetMapping("create-supplier")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/supplier/create");
        modelAndView.addObject("supplier", new Supplier());
        return modelAndView;
    }

    @PostMapping("create-supplier")
    public ModelAndView saveSupplier(@ModelAttribute("supplier") Supplier supplier){
        supplierService.save(supplier);

        ModelAndView modelAndView = new ModelAndView("/supplier/create");
        modelAndView.addObject("supplier", new Supplier());
        modelAndView.addObject("message", "New supplier added successfully");
        return modelAndView;
    }

    @GetMapping("/edit-supplier/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        Supplier supplier = supplierService.findById(id);
        if(supplier != null){
            ModelAndView modelAndView = new ModelAndView("/supplier/edit");
            modelAndView.addObject("supplier", supplier);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-supplier")
    public ModelAndView updateSupplier(@ModelAttribute("supplier") Supplier supplier){
        supplierService.save(supplier);
        ModelAndView modelAndView = new ModelAndView("/supplier/edit");
        modelAndView.addObject("supplier", supplier);
        modelAndView.addObject("message", "Supplier information update successfully");
        return modelAndView;
    }

    @GetMapping("/delete-supplier/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        Supplier supplier = supplierService.findById(id);
        if(supplier != null){
            ModelAndView modelAndView = new ModelAndView("/supplier/delete");
            modelAndView.addObject("supplier", supplier);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-supplier")
    public String deleteProvince(@ModelAttribute("supplier") Supplier supplier){
        supplierService.remove(supplier.getId());
        return "redirect:suppliers";
    }

    @GetMapping("/view-supplier/{id}")
    public ModelAndView viewSupplier(@PathVariable("id") Long id){
        Supplier supplier = supplierService.findById(id);
        if(supplier == null){
            return new ModelAndView("/error.404");
        }

        Iterable<Material> materials = materialService.findAllBySupplier(supplier);

        ModelAndView modelAndView = new ModelAndView("/supplier/view");
        modelAndView.addObject("supplier", supplier);
        modelAndView.addObject("materials", materials);
        return modelAndView;
    }
}
