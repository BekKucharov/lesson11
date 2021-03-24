package uz.pdp.springbootwarehouseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springbootwarehouseproject.entity.Currency;

public interface CurrencyRepository extends JpaRepository<Currency, Integer> {

    boolean existsByName(String name);
}
