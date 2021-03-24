package uz.pdp.springbootwarehouseproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springbootwarehouseproject.entity.Measurement;
import uz.pdp.springbootwarehouseproject.payload.Result;
import uz.pdp.springbootwarehouseproject.repository.MeasurementRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MeasurementService {
    @Autowired
    MeasurementRepository measurementRepository;

    public Result addMeasurementService(Measurement measurement){
        boolean existsByName = measurementRepository.existsByName(measurement.getName());
        if (existsByName) return new Result("This measurement is already exists", false);

        Measurement measurement1 = new Measurement();
        measurement1.setName(measurement.getName());
        measurementRepository.save(measurement1);
        return new Result("Measurement successfully added", true);
    }

    public List<Measurement> getMeasurement(){
        return measurementRepository.findAll();
    }

    public Measurement getMeasurementById(Integer id){
        Optional<Measurement> measurementId = measurementRepository.findById(id);
        if (!measurementId.isPresent()) return new Measurement();
        Measurement measurement = measurementId.get();
        return measurement;
    }

    public Result editMeasurement(Integer id, Measurement measurement){
        Optional<Measurement> measurementId = measurementRepository.findById(id);
        if (!measurementId.isPresent())
            return new Result("Measurement not found", false);
        Measurement editingMeasurement = measurementId.get();
        editingMeasurement.setName(measurement.getName());
        measurementRepository.save(editingMeasurement);
        return new Result("Measurement edited", true);
    }

    public Result delMeasurement(Integer id){
        Optional<Measurement> measurementId = measurementRepository.findById(id);
        if (!measurementId.isPresent())
            return new Result("Measurement not found", false);
        measurementRepository.deleteById(id);
        return new Result("Measurement deleted", true);
    }
}
