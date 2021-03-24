package uz.pdp.springbootwarehouseproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springbootwarehouseproject.entity.Product;
import uz.pdp.springbootwarehouseproject.entity.User;
import uz.pdp.springbootwarehouseproject.entity.Warehouse;
import uz.pdp.springbootwarehouseproject.payload.Result;
import uz.pdp.springbootwarehouseproject.payload.UserDto;
import uz.pdp.springbootwarehouseproject.repository.UserRepository;
import uz.pdp.springbootwarehouseproject.repository.WarehouseRepository;

import java.util.*;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    WarehouseRepository warehouseRepository;

    public List<User> getUser(){
        return userRepository.findAll();
    }
    public User getUserById(Integer id){
        Optional<User> userId = userRepository.findById(id);
        if (userId.isPresent()) return new User();
        User user = userId.get();
        return user;
    }
    public Result addUser(UserDto userDto){
        boolean exists = userRepository.existsByFirstNameAndLastName(userDto.getFirstName(), userDto.getLastName());
        if (exists) return new Result("User with these name and surname is already exists", false);
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setPassword(userDto.getPassword());

        Set<Warehouse> warehouseSet = new LinkedHashSet<>();
        Set<Integer> warehouseId = userDto.getWarehouseId();

        for (Integer ids : warehouseId) {
            Optional<Warehouse> warehouseIds = warehouseRepository.findById(ids);
            if (!warehouseIds.isPresent()) return new Result("Warehouse not found", false);

            Warehouse warehouse = warehouseIds.get();
            warehouseSet.add(warehouse);
        }
        user.setWarehouses(warehouseSet);
        userRepository.save(user);
        return new Result("User added successfully", true);
    }
    public Result editUser(Integer id, UserDto userDto){
        Optional<User> userId = userRepository.findById(id);
        if (!userId.isPresent()) return new Result("User not found", false);

        boolean exists = userRepository.existsByFirstNameAndLastName(userDto.getFirstName(), userDto.getLastName());
        if (exists) return new Result("User with these name and surname is already exists", false);
        User editingUser = userId.get();
        editingUser.setFirstName(userDto.getFirstName());
        editingUser.setLastName(userDto.getLastName());
        editingUser.setPhoneNumber(userDto.getPhoneNumber());
        editingUser.setPassword(userDto.getPassword());

        Set<Warehouse> warehouseSet = new LinkedHashSet<>();
        Set<Integer> warehouseId = userDto.getWarehouseId();

        for (Integer ids : warehouseId) {
            Optional<Warehouse> warehouseIds = warehouseRepository.findById(ids);
            if (!warehouseIds.isPresent()) return new Result("Warehouse not found", false);

            Warehouse warehouse = warehouseIds.get();
            warehouseSet.add(warehouse);
        }
        editingUser.setWarehouses(warehouseSet);
        userRepository.save(editingUser);
        return new Result("User edited", true);
    }
    public Result delUser(Integer id){
        Optional<User> userId = userRepository.findById(id);
        if (!userId.isPresent()) return new Result("User not found", false);
        userRepository.deleteById(id);
        return new Result("User deleted", true);
    }

}
