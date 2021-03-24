package uz.pdp.springbootwarehouseproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springbootwarehouseproject.entity.Supplier;
import uz.pdp.springbootwarehouseproject.payload.Result;
import uz.pdp.springbootwarehouseproject.payload.SupplierDto;
import uz.pdp.springbootwarehouseproject.repository.SupplierRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {
    @Autowired
    SupplierRepository supplierRepository;

    public List<Supplier> getSupplier(){
        return supplierRepository.findAll();
    }
    public Supplier getSupplierById(Integer id){
        Optional<Supplier> supplierId = supplierRepository.findById(id);
        if (!supplierId.isPresent()) return new Supplier();
        Supplier supplier = supplierId.get();
        return supplier;
    }
    public Result addSupplier(SupplierDto supplierDto){
        boolean exists = supplierRepository.existsByPhoneNumber(supplierDto.getPhoneNumber());
        if (exists) return new Result("This supplier is already exists", false);
        Supplier supplier = new Supplier();
        supplier.setName(supplierDto.getName());
        supplier.setPhoneNumber(supplierDto.getPhoneNumber());
        supplierRepository.save(supplier);
        return new Result("Supplier added", true);
    }
    public Result editSupplier(Integer id, SupplierDto supplierDto){
        Optional<Supplier> supplierId = supplierRepository.findById(id);
        if (!supplierId.isPresent()) return new Result("Supplier not found", false);
        Supplier editingSupplier = supplierId.get();
        editingSupplier.setName(supplierDto.getName());
        editingSupplier.setPhoneNumber(supplierDto.getPhoneNumber());
        supplierRepository.save(editingSupplier);
        return new Result("Supplier info edited", true);
    }
    public Result delSupplier(Integer id){
        Optional<Supplier> supplierId = supplierRepository.findById(id);
        if (!supplierId.isPresent()) return new Result("Supplier not found", false);
        supplierRepository.deleteById(id);
        return new Result("Supplier deleted", true);
    }
}
