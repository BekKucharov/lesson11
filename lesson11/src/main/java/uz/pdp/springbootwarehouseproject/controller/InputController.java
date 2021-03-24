package uz.pdp.springbootwarehouseproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springbootwarehouseproject.entity.Input;
import uz.pdp.springbootwarehouseproject.payload.InputDto;
import uz.pdp.springbootwarehouseproject.payload.Result;
import uz.pdp.springbootwarehouseproject.service.InputService;

import java.util.List;

@RestController
@RequestMapping("/input")
public class InputController {
    @Autowired
    InputService inputService;

    @GetMapping
    public List<Input> getInput(){
        return inputService.getInput();
    }
    @GetMapping("/{id}")
    public Input getInputById(@PathVariable Integer id){
        return inputService.getInputById(id);
    }
    @PostMapping
    public Result addInput(@RequestBody InputDto inputDto){
        return inputService.addInput(inputDto);
    }
    @PutMapping("/{id}")
    public Result editInput(@PathVariable Integer id, @RequestBody InputDto inputDto){
        return inputService.editInput(id, inputDto);
    }
    @DeleteMapping("/{id}")
    public Result delInput(@PathVariable Integer id){
        return inputService.delInput(id);
    }

}
