package uz.pdp.springbootwarehouseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springbootwarehouseproject.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
    boolean existsByPhoneNumber(String phoneNumber);
}
