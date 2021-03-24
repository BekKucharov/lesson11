package uz.pdp.springbootwarehouseproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springbootwarehouseproject.entity.Client;
import uz.pdp.springbootwarehouseproject.payload.ClientDto;
import uz.pdp.springbootwarehouseproject.payload.Result;
import uz.pdp.springbootwarehouseproject.service.ClientService;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    ClientService clientService;

    @GetMapping
    public List<Client> getClient(){
        return clientService.getClient();
    }
    @GetMapping("/{id}")
    public Client getClient(@PathVariable Integer id){
        return clientService.getClientById(id);
    }
    @PostMapping
    public Result addClient(@RequestBody ClientDto clientDto){
        return clientService.addClient(clientDto);
    }
    @PutMapping("/{id}")
    public Result editClient(@PathVariable Integer id, @RequestBody ClientDto clientDto){
        return clientService.editClient(id, clientDto);
    }
    @DeleteMapping("/{id}")
    public Result delClient(@PathVariable Integer id){
        return clientService.delClient(id);
    }
}
