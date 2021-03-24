package uz.pdp.springbootwarehouseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springbootwarehouseproject.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    boolean existsByName(String name);
}
