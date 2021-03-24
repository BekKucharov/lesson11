package uz.pdp.springbootwarehouseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springbootwarehouseproject.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByFirstNameAndLastName(String firstName, String lastName);
}
