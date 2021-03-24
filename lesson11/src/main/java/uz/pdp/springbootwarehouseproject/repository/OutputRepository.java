package uz.pdp.springbootwarehouseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springbootwarehouseproject.entity.Output;

public interface OutputRepository extends JpaRepository<Output, Integer> {
    boolean existsByFactureNumber(String factureNumber);

}
