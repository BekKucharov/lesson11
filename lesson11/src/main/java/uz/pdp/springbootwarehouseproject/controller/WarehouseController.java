package uz.pdp.springbootwarehouseproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springbootwarehouseproject.entity.Warehouse;
import uz.pdp.springbootwarehouseproject.payload.Result;
import uz.pdp.springbootwarehouseproject.service.WarehouseService;

import java.util.List;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {
    @Autowired
    WarehouseService warehouseService;

    @GetMapping
    public List<Warehouse> getWarehouse(){
        return warehouseService.getWarehouse();
    }
    @GetMapping("/{id}")
    public Warehouse getWarehouseById(@PathVariable Integer id){
        return warehouseService.getWarehouseById(id);
    }
    @PostMapping
    public Result addWarehouse(@RequestBody Warehouse warehouse){
        return warehouseService.addWarehouse(warehouse);
    }
    @PutMapping("/{id}")
    public Result editWarehouse(@PathVariable Integer id, @RequestBody Warehouse warehouse){
        return warehouseService.editWarehouse(id, warehouse);
    }
    @DeleteMapping("/{id}")
    public Result delWarehouse(@PathVariable Integer id){
        return warehouseService.delWarehouse(id);
    }

}
