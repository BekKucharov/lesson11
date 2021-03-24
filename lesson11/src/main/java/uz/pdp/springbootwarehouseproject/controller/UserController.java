package uz.pdp.springbootwarehouseproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springbootwarehouseproject.entity.User;
import uz.pdp.springbootwarehouseproject.payload.Result;
import uz.pdp.springbootwarehouseproject.payload.UserDto;
import uz.pdp.springbootwarehouseproject.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public List<User> getUser(){
        return userService.getUser();
    }
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id){
        return userService.getUserById(id);
    }
    @PostMapping
    public Result addUser(@RequestBody UserDto userDto){
        return userService.addUser(userDto);
    }
    @PutMapping("/{id}")
    public Result editUser(@PathVariable Integer id, @RequestBody UserDto userDto){
        return userService.editUser(id, userDto);
    }
    @DeleteMapping("/{id}")
    public Result delUser(@PathVariable Integer id){
        return userService.delUser(id);
    }
}
