package uz.pdp.springbootwarehouseproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springbootwarehouseproject.entity.OutputProduct;
import uz.pdp.springbootwarehouseproject.payload.OutputProductDto;
import uz.pdp.springbootwarehouseproject.payload.Result;
import uz.pdp.springbootwarehouseproject.service.OutputProductService;

import java.util.List;

@RestController
@RequestMapping("/outputProduct")
public class OutputProductController {
    @Autowired
    OutputProductService outputProductService;

    @GetMapping
    public List<OutputProduct> get(){
        return outputProductService.getOutputProduct();
    }
    @GetMapping("/{id}")
    public OutputProduct get(@PathVariable Integer id){
        return outputProductService.getOutputProduct(id);
    }
    @PostMapping
    public Result add(@RequestBody OutputProductDto outputProductDto){
        return outputProductService.addOutputProduct(outputProductDto);
    }
    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody OutputProductDto outputProductDto){
        return outputProductService.editOutputProduct(id, outputProductDto);
    }
    @DeleteMapping("/{id}")
    public Result del(@PathVariable Integer id){
        return outputProductService.delOutputProduct(id);
    }


}
