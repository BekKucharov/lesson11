package uz.pdp.springbootwarehouseproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springbootwarehouseproject.entity.Currency;
import uz.pdp.springbootwarehouseproject.entity.Input;
import uz.pdp.springbootwarehouseproject.entity.Supplier;
import uz.pdp.springbootwarehouseproject.entity.Warehouse;
import uz.pdp.springbootwarehouseproject.payload.InputDto;
import uz.pdp.springbootwarehouseproject.payload.Result;
import uz.pdp.springbootwarehouseproject.repository.CurrencyRepository;
import uz.pdp.springbootwarehouseproject.repository.InputRepository;
import uz.pdp.springbootwarehouseproject.repository.SupplierRepository;
import uz.pdp.springbootwarehouseproject.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InputService {
    @Autowired
    InputRepository inputRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    CurrencyRepository currencyRepository;

    public List<Input> getInput(){
        return inputRepository.findAll();
    }
    public Input getInputById(Integer id){
        Optional<Input> inputId = inputRepository.findById(id);
        if (!inputId.isPresent()) return new Input();
        Input input = inputId.get();
        return input;
    }
    public Result addInput(InputDto inputDto){
        boolean exists = inputRepository.existsByFactureNumber(inputDto.getFactureNumber());
        if (exists) return new Result("Input with this Facture Number is already exists", false);
        Optional<Warehouse> warehouseId = warehouseRepository.findById(inputDto.getWarehouseId());
        if (!warehouseId.isPresent()) return new Result("Warehouse not found", false);
        Optional<Supplier> supplierId = supplierRepository.findById(inputDto.getSupplierId());
        if (!supplierId.isPresent()) return new Result("Supplier not found", false);
        Optional<Currency> currencyId = currencyRepository.findById(inputDto.getCurrencyId());
        if (!currencyId.isPresent()) return new Result("Currency not found", false);

        Input input = new Input();
        input.setDate(inputDto.getDate());
        input.setFactureNumber(inputDto.getFactureNumber());
        input.setWarehouse(warehouseId.get());
        input.setSupplier(supplierId.get());
        input.setCurrency(currencyId.get());
        inputRepository.save(input);
        return new Result("Input saved", true);
    }
    public Result editInput(Integer id, InputDto inputDto){
        Optional<Input> inputId = inputRepository.findById(id);
        if (!inputId.isPresent()) return new Result("Input not found", false);
        boolean exists = inputRepository.existsByFactureNumber(inputDto.getFactureNumber());
        if (exists) return new Result("Input with this Facture Number is already exists", false);
        Optional<Warehouse> warehouseId = warehouseRepository.findById(inputDto.getWarehouseId());
        if (!warehouseId.isPresent()) return new Result("Warehouse not found", false);
        Optional<Supplier> supplierId = supplierRepository.findById(inputDto.getSupplierId());
        if (!supplierId.isPresent()) return new Result("Supplier not found", false);
        Optional<Currency> currencyId = currencyRepository.findById(inputDto.getCurrencyId());
        if (!currencyId.isPresent()) return new Result("Currency not found", false);

        Input editingInput = inputId.get();
        editingInput.setDate(inputDto.getDate());
        editingInput.setFactureNumber(inputDto.getFactureNumber());
        editingInput.setWarehouse(warehouseId.get());
        editingInput.setSupplier(supplierId.get());
        editingInput.setCurrency(currencyId.get());
        inputRepository.save(editingInput);
        return new Result("Input edited", true);
    }
    public Result delInput(Integer id){
        Optional<Input> inputId = inputRepository.findById(id);
        if (inputId.isPresent()) return new Result("Input not found", false);
        inputRepository.deleteById(id);
        return new Result("Input deleted", true);
    }
}
