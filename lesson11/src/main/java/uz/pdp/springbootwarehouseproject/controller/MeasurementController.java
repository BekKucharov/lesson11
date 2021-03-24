package uz.pdp.springbootwarehouseproject.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springbootwarehouseproject.entity.Measurement;
import uz.pdp.springbootwarehouseproject.payload.Result;
import uz.pdp.springbootwarehouseproject.repository.MeasurementRepository;
import uz.pdp.springbootwarehouseproject.service.MeasurementService;

import java.util.List;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {
    
    @Autowired
    MeasurementService measurementService;

    @GetMapping
    public List<Measurement> getMeasurement(){
        return measurementService.getMeasurement();
    }

    @GetMapping("/{id}")
    public Measurement getMeasurement(@PathVariable Integer id){
        return measurementService.getMeasurementById(id);
    }

    @PostMapping
    public Result addMeasurementController(@RequestBody Measurement measurement){
        return measurementService.addMeasurementService(measurement);
    }

    @PutMapping("/{id}")
    public Result editMeasurement(@PathVariable Integer id, @RequestBody Measurement measurement){
        return measurementService.editMeasurement(id, measurement);
    }

    @DeleteMapping("/{id}")
    public Result delMeasurement(@PathVariable Integer id){
        return measurementService.delMeasurement(id);
    }
}
