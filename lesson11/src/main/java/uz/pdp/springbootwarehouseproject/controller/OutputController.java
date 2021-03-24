package uz.pdp.springbootwarehouseproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springbootwarehouseproject.entity.Output;
import uz.pdp.springbootwarehouseproject.payload.OutputDto;
import uz.pdp.springbootwarehouseproject.payload.Result;
import uz.pdp.springbootwarehouseproject.service.OutputService;

import java.util.List;

@RestController
@RequestMapping("/output")
public class OutputController {
    @Autowired
    OutputService outputService;

    @GetMapping
    public List<Output> get(){
        return outputService.getOutput();
    }
    @GetMapping("/{id}")
    public Output get(@PathVariable Integer id){
        return outputService.getOutput(id);
    }
    @PostMapping
    public Result add(@RequestBody OutputDto outputDto){
        return outputService.addOutput(outputDto);
    }
    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody OutputDto outputDto){
        return outputService.editOutput(id, outputDto);
    }
    @DeleteMapping("/{id}")
    public Result del(@PathVariable Integer id){
        return outputService.delOutput(id);
    }


}
