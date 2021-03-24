package uz.pdp.springbootwarehouseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springbootwarehouseproject.entity.InputProduct;

public interface InputProductRepository extends JpaRepository<InputProduct, Integer> {

}
