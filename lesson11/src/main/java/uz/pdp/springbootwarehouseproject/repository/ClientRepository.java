package uz.pdp.springbootwarehouseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springbootwarehouseproject.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
