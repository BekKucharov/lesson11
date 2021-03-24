package uz.pdp.springbootwarehouseproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Input {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String date;

    private String factureNumber;

    @ManyToOne
    private Warehouse warehouse;

    @ManyToOne
    private Supplier supplier;


    @ManyToOne
    private Currency currency;

    @Column(nullable = false, unique = true)
    private UUID uuid = UUID.randomUUID();

}
