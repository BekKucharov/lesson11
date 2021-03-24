package uz.pdp.springbootwarehouseproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springbootwarehouseproject.entity.Warehouse;
import uz.pdp.springbootwarehouseproject.payload.Result;
import uz.pdp.springbootwarehouseproject.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {
    @Autowired
    WarehouseRepository warehouseRepository;

    public List<Warehouse> getWarehouse(){
        return warehouseRepository.findAll();
    }
    public Warehouse getWarehouseById(Integer id){
        Optional<Warehouse> warehouseId = warehouseRepository.findById(id);
        if (!warehouseId.isPresent()) return new Warehouse();
        Warehouse warehouse = warehouseId.get();
        return warehouse;
    }
    public Result addWarehouse(Warehouse warehouseDto){
        boolean exists = warehouseRepository.existsByName(warehouseDto.getName());
        if (exists) return new Result("This kind warehouse is already exists", false);
        Warehouse warehouse = new Warehouse();
        warehouse.setName(warehouseDto.getName());
        warehouseRepository.save(warehouse);
        return new Result("Warehouse added", true);
    }
    public Result editWarehouse(Integer id, Warehouse warehouseDto){
        Optional<Warehouse> warehouseId = warehouseRepository.findById(id);
        if (warehouseId.isPresent()) return new Result("Warehouse not found", false);
        boolean exists = warehouseRepository.existsByName(warehouseDto.getName());
        if (exists) return new Result("This kind warehouse is already exists", false);
        Warehouse editingWarehouse = warehouseId.get();
        editingWarehouse.setName(warehouseDto.getName());
        warehouseRepository.save(editingWarehouse);
        return new Result("Warehouse info edited", true);
    }
    public Result delWarehouse(Integer id){
        Optional<Warehouse> warehouseId = warehouseRepository.findById(id);
        if (!warehouseId.isPresent()) return new Result("Warehouse not found", false);
        warehouseRepository.deleteById(id);
        return new Result("Warehouse deleted", true);
    }
}
