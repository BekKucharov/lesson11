package uz.pdp.springbootwarehouseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springbootwarehouseproject.entity.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {
}
