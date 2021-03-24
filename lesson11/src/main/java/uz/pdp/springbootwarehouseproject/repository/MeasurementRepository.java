package uz.pdp.springbootwarehouseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springbootwarehouseproject.entity.Measurement;

public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {

    boolean existsByName(String name);
}
