package uz.pdp.springbootwarehouseproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springbootwarehouseproject.entity.*;
import uz.pdp.springbootwarehouseproject.payload.OutputDto;
import uz.pdp.springbootwarehouseproject.payload.Result;
import uz.pdp.springbootwarehouseproject.repository.ClientRepository;
import uz.pdp.springbootwarehouseproject.repository.CurrencyRepository;
import uz.pdp.springbootwarehouseproject.repository.OutputRepository;
import uz.pdp.springbootwarehouseproject.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OutputService {
    @Autowired
    OutputRepository outputRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    CurrencyRepository currencyRepository;
    @Autowired
    ClientRepository clientRepository;

    public List<Output> getOutput(){
        return outputRepository.findAll();
    }
    public Output getOutput(Integer id){
        Optional<Output> byId = outputRepository.findById(id);
        if (!byId.isPresent()) return new Output();
        Output output = byId.get();
        return output;
    }
    public Result addOutput(OutputDto outputDto){
        boolean exists = outputRepository.existsByFactureNumber(outputDto.getFactureNumber());
        if (exists) return new Result("Outpur is already exists", false);
        Optional<Warehouse> warehouseId = warehouseRepository.findById(outputDto.getWarehouseId());
        if (!warehouseId.isPresent()) return new Result("Warehouse not found", false);
        Optional<Client> clientId = clientRepository.findById(outputDto.getClientId());
        if (!clientId.isPresent()) return new Result("Client not found", false);
        Optional<Currency> currencyId = currencyRepository.findById(outputDto.getCurrencyId());
        if (!currencyId.isPresent()) return new Result("Currency not found", false);

        Output output = new Output();
        output.setDate(outputDto.getDate());
        output.setFactureNumber(outputDto.getFactureNumber());
        output.setWarehouse(warehouseId.get());
        output.setClient(clientId.get());
        output.setCurrency(currencyId.get());
        outputRepository.save(output);
        return new Result("Output added successfully", true);
    }
    public Result editOutput(Integer id, OutputDto outputDto){
        Optional<Output> outputId = outputRepository.findById(id);
        if (!outputId.isPresent()) return new Result("Output not found", false);
        boolean exists = outputRepository.existsByFactureNumber(outputDto.getFactureNumber());
        if (exists) return new Result("Outpur is already exists", false);
        Optional<Warehouse> warehouseId = warehouseRepository.findById(outputDto.getWarehouseId());
        if (!warehouseId.isPresent()) return new Result("Warehouse not found", false);
        Optional<Client> clientId = clientRepository.findById(outputDto.getClientId());
        if (!clientId.isPresent()) return new Result("Client not found", false);
        Optional<Currency> currencyId = currencyRepository.findById(outputDto.getCurrencyId());
        if (!currencyId.isPresent()) return new Result("Currency not found", false);

        Output output = outputId.get();

        output.setDate(outputDto.getDate());
        output.setFactureNumber(outputDto.getFactureNumber());
        output.setWarehouse(warehouseId.get());
        output.setClient(clientId.get());
        output.setCurrency(currencyId.get());
        outputRepository.save(output);
        return new Result("Output edited successfully", true);
    }
    public Result delOutput(Integer id){
        Optional<Output> byId = outputRepository.findById(id);
        if (!byId.isPresent()) return new Result("Output not found", false);
        outputRepository.deleteById(id);
        return new Result("Output deleted", true);
    }
}
