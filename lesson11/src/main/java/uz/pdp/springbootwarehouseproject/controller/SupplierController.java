package uz.pdp.springbootwarehouseproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springbootwarehouseproject.entity.Supplier;
import uz.pdp.springbootwarehouseproject.payload.Result;
import uz.pdp.springbootwarehouseproject.payload.SupplierDto;
import uz.pdp.springbootwarehouseproject.service.SupplierService;

import java.util.List;

@RestController
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    SupplierService supplierService;

    @GetMapping
    public List<Supplier> getSupplier(){
        return supplierService.getSupplier();
    }
    @GetMapping("/{id}")
    public Supplier getSupplier(@PathVariable Integer id){
        return supplierService.getSupplierById(id);
    }
    @PostMapping
    public Result addSupplier(@RequestBody SupplierDto supplierDto){
        return supplierService.addSupplier(supplierDto);
    }
    @PutMapping("/{id}")
    public Result editSupplier(@PathVariable Integer id, @RequestBody SupplierDto supplierDto){
        return supplierService.editSupplier(id, supplierDto);
    }
    @DeleteMapping("/{id}")
    public Result delSupplier(@PathVariable Integer id){
        return supplierService.delSupplier(id);
    }
}
