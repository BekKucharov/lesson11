package uz.pdp.springbootwarehouseproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springbootwarehouseproject.entity.InputProduct;
import uz.pdp.springbootwarehouseproject.payload.InputProductDto;
import uz.pdp.springbootwarehouseproject.payload.Result;
import uz.pdp.springbootwarehouseproject.service.InputProductService;

import java.util.List;

@RestController
@RequestMapping("/inputProduct")
public class InputProductController {
    @Autowired
    InputProductService inputProductService;

    @GetMapping
    public List<InputProduct> get(){
        return inputProductService.getInputProduct();
    }
    @GetMapping("/{id}")
    public InputProduct get(@PathVariable Integer id){
        return inputProductService.getInputProductById(id);
    }
    @PostMapping
    public Result add(@RequestBody InputProductDto inputProductDto){
        return inputProductService.addInputProduct(inputProductDto);
    }
    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody InputProductDto inputProductDto){
        return inputProductService.editInputProduct(id, inputProductDto);
    }
    @DeleteMapping("/{id}")
    public Result del(@PathVariable Integer id){
        return inputProductService.delInputProduct(id);
    }

}
