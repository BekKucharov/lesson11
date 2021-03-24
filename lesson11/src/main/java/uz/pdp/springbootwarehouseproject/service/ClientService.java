package uz.pdp.springbootwarehouseproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springbootwarehouseproject.entity.Client;
import uz.pdp.springbootwarehouseproject.payload.ClientDto;
import uz.pdp.springbootwarehouseproject.payload.Result;
import uz.pdp.springbootwarehouseproject.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;

    public List<Client> getClient(){
        return clientRepository.findAll();
    }
    public Client getClientById(Integer id){
        Optional<Client> clientId = clientRepository.findById(id);
        if (!clientId.isPresent()) return new Client();
        Client client = clientId.get();
        return client;
    }
    public Result addClient(ClientDto clientDto){
        Client client = new Client();
        client.setName(clientDto.getName());
        client.setPhoneNumber(clientDto.getPhoneNumber());
        clientRepository.save(client);
        return new Result("Client added successfully", true);
    }
    public Result editClient(Integer id, ClientDto clientDto){
        Optional<Client> clientId = clientRepository.findById(id);
        if (!clientId.isPresent())
            return new Result("Client not found", false);
        Client editingClient = clientId.get();
        editingClient.setName(clientDto.getName());
        editingClient.setPhoneNumber(clientDto.getPhoneNumber());
        clientRepository.save(editingClient);
        return new Result("Client info edited", true);
    }
    public Result delClient(Integer id){
        Optional<Client> clientId = clientRepository.findById(id);
        if (!clientId.isPresent())
            return new Result("Client not found", false);
        clientRepository.deleteById(id);
        return new Result("Client info deleted", true);
    }
}
