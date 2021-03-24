package uz.pdp.springbootwarehouseproject.payload;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import uz.pdp.springbootwarehouseproject.entity.Client;
import uz.pdp.springbootwarehouseproject.entity.Currency;
import uz.pdp.springbootwarehouseproject.entity.Output;
import uz.pdp.springbootwarehouseproject.entity.Warehouse;
import uz.pdp.springbootwarehouseproject.repository.OutputRepository;

import java.util.List;
import java.util.Optional;

@Data
public class OutputDto {
    private String date;
    private String factureNumber;
    private Integer warehouseId;
    private Integer clientId;
    private Integer currencyId;
}
