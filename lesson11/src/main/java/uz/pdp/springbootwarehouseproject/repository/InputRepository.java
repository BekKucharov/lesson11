package uz.pdp.springbootwarehouseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springbootwarehouseproject.entity.Input;

public interface InputRepository extends JpaRepository<Input, Integer> {
    boolean existsByFactureNumber(String factureNumber);

}
